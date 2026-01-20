package Logic;

import Enums.ServiceType;
import Interfaces.IHospital;
import Interfaces.IEvent;
import Interfaces.IEmergencyUnit;
import Interfaces.ITicket;
import Interfaces.IDispatcher;
import Model.Ticket;
import Units.Ambulance;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class EmergencyDispatcher implements IDispatcher{
    private String name;
    private List<IEmergencyUnit> allUnits;
    private List<IHospital> hospitals;
    private List<ITicket> activeTickets;

    public EmergencyDispatcher(String Name){
        this.name = Name;
        this.allUnits = new ArrayList<>();
        this.hospitals = new ArrayList<>();
        this.activeTickets = new ArrayList<>();
    }

    @Override
    public void addUnit(IEmergencyUnit unit){
        this.allUnits.add(unit);
        if (unit instanceof Ambulance){
            ((Ambulance) unit).setHospitals((this.hospitals));
        }
    }

    @Override
    public void addHospital(IHospital hospital){
        this.hospitals.add(hospital);
    }

    @Override
    public void receiveEvent(IEvent event, int turnsRemaining) {
        System.out.println("Otrzymano zgłoszenie: " + event.getEventName());

        ITicket ticket = new Ticket(event, turnsRemaining);
        this.activeTickets.add(ticket);

        this.dispatchUnits(this.allUnits);
    }

    @Override
    public void dispatchUnits(List<IEmergencyUnit> units) {
        for (ITicket ticket : activeTickets) {
            Map<ServiceType, Integer> requirements = ticket.getEvent().getRequiredUnits();

            for (ServiceType type : requirements.keySet()) {
                int needed = requirements.get(type);
                int found = 0;

                for (IEmergencyUnit unit : units) {
                    if (found < needed && unit.isAvailable() && unit.getServiceType() == type) {
                        unit.assignToTicket(ticket);
                        found++;
                    }
                }
            }
        }
    }

    @Override
    public void sendReport() {
        System.out.println("============RAPORT DYSPOZYTORA============");
        System.out.println("===========================================");
        System.out.println("Aktywne zdarzenia: " + activeTickets.size());

        int busyTickets = 0;
        for (IEmergencyUnit unit: allUnits){
            if(!unit.isAvailable()) {
                busyTickets++;
            }
        }
        System.out.println("Jednostki aktualnie w akcji: " + busyTickets);

        for(IHospital hospital: hospitals) {
            System.out.println("Szpital: " + hospital.getName() + ". " + "Wolne lozka: " + hospital.getAvailableBeds());
        }
        System.out.println("===========================================");
    }

    @Override
    public String toString() {
        return this.name.toUpperCase();
    }

    @Override
    public void updateTurns() {
        for(IEmergencyUnit unit: allUnits){
            unit.performAction();
        }

        for(IHospital hospital: hospitals) {
            hospital.updateStatus();
        }

        activeTickets.removeIf(ticket -> {
                ticket.processTurn();

                if(ticket.isFinished()) {
                    System.out.println("Zgloszenie " + ticket.getEvent().getEventName() + " zostalo zakonczone\n");
                    for(IEmergencyUnit unit: allUnits){
                        unit.releaseTicket();
                    }
                    return true;
                }
                return false;
            });
        }
    }



