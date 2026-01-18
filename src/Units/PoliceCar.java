package Units;

import Enums.ServiceType;
import Interfaces.IEmergencyUnit;
import Interfaces.ITicket;

public class PoliceCar implements IEmergencyUnit {
    private String id;
    private boolean available;
    private ITicket currentTicket = null;
    private ServiceType serviceType;


    public PoliceCar(String id) {
        this.id = id;
        this.serviceType = ServiceType.POLICE;
        this.available = true;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public ServiceType getServiceType() {
        return ServiceType.POLICE;
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
        if(currentTicket != null) {
            System.out.println("Patrol " + id + " akutalnie zabezpiecza teren zdarzenia\n");
            this.available = false;
        }
    }

    @Override
    public void releaseTicket() {
        this.currentTicket = null;
        this.available = true;
    }
}
