package Units;

import Enums.ServiceType;
import Interfaces.IEmergencyUnit;
import Interfaces.IHospital;
import Interfaces.IPatient;
import Interfaces.ITicket;
import java.util.List;

public class Ambulance implements IEmergencyUnit {
    private String id;
    private boolean available;
    private ITicket currentTicket;
    private IPatient currentPatient;
    private ServiceType serviceType;
    private List<IHospital> knownHospitals;


    public Ambulance(String id) {
        this.id = id;
        this.serviceType = ServiceType.MEDICAL;
        this.available = true;
    }

    public void setHospitals(List<IHospital> hospitals) {
        this.knownHospitals = hospitals;
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
                if(p.patientLocation() != null && p.patientLocation().equals("Miejsce zdarzenia")) {
                    currentPatient = p;
                    p.setLocation("W karetce " + this.id);
                    break;
                }
            }
        }

        if (currentPatient != null) {
            System.out.println("Jednostka " + id + " zabiera pacjenta: " + currentPatient.getFirstName() + "\n");

            if(knownHospitals != null) {
                for(IHospital h: knownHospitals) {
                    if(h.getAvailableBeds() > 0){
                        boolean success = h.admitPatient(currentPatient);
                        if(success) {
                            System.out.println(" -> SUKCES: Pacjent przekazany do: " + h.getName() + "\n");
                            currentTicket.addHelpedVictims();
                            currentPatient = null;
                            return;
                        }
                    }
                }
                System.out.println(" -> OSTRZEŻENIE: Brak miejsc w szpitalach!\n");
            } else {
                System.out.println(" -> BŁĄD: Karetka nie zna szpitali!\n");
            }

        }
    }

    @Override
    public void releaseTicket() {
        this.currentTicket = null;
        this.currentPatient = null;
        this.available = true;
    }
}
