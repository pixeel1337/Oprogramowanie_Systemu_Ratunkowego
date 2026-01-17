package Units;

import Enums.ServiceType;
import Interfaces.IEmergencyUnit;
import Interfaces.ITicket;

public class FireTruck implements IEmergencyUnit {
    private String id;
    private boolean available = true;
    private ITicket currentTicket = null;

    public FireTruck(String id) {
        this.id = id;
    }



    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public ServiceType getServiceType() {
        return ServiceType.FIRE;
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
        if(this.currentTicket != null) {
            System.out.println("Jednostka " + id + "akutalnie usuwa skutki zdarzenia/gasi pozar");
        }
    }

    @Override
    public void releaseTicket() {
        this.currentTicket = null;
        this.available = true;
    }
}
