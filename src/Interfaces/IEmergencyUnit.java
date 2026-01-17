package Interfaces;

import Enums.ServiceType;

public interface IEmergencyUnit {
    String getID();

    ServiceType getServiceType();

    boolean isAvailable();

    void assignToTicket(ITicket ticket);

    void performAction();

    void releaseTicket();
}
