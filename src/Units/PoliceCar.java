package Units;

import Enums.ServiceType;
import Interfaces.IEmergencyUnit;
import Interfaces.ITicket;

public class PoliceCar implements IEmergencyUnit {
    private String id;
    private boolean available = true;
    private ITicket currentTicket = null;

    public PoliceCar(String id) {
        this.id = id;
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
            System.out.println("Patrol " + id + "akutalnie zabezpiecza teren zdarzenia");
        }
    }

    @Override
    public void releaseTicket() {
        this.currentTicket = null;
        this.available = true;
    }
}
