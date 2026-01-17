package Units;

import Enums.ServiceType;
import Interfaces.IEmergencyUnit;
import Interfaces.IPatient;
import Interfaces.ITicket;
import java.util.List;

public class Ambulance implements IEmergencyUnit {
    private String id;
    private boolean available = true;
    private ITicket currentTicket;
    private IPatient currentPatient;

    public Ambulance(String id) {
        this.id = id;
    }


    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public ServiceType getServiceType() {
        return ServiceType.MEDICAL;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public void assignToTicket(ITicket ticket) {
        this.currentTicket = ticket;
        this.available = false;
    }

    @Override
    public void performAction() {
        if (currentTicket == null || currentTicket.isFinished()) {
            this.available = true;
            return;
        }

        if (currentPatient == null) {
            List<IPatient> victims = currentTicket.getEvent().getVictims();
            for(IPatient p : victims) {
                if(p.patientLocation().equals("Miejsce zdarzenia")) {
                    currentPatient = p;
                    break;
                }
            }
        }

        if(currentPatient != null) {
            System.out.println("Jednostka " + id + "zabiera pacjenta: " + currentPatient.getFirstName());

            currentPatient.setLocation("W transporcie");

            currentTicket.addHelpedVictims();

            currentPatient = null;
        }
    }

    @Override
    public void releaseTicket() {
        this.currentTicket = null;
        this.currentPatient = null;
        this.available = true;
    }
}
