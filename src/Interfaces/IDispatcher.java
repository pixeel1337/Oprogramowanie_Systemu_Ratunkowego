package Interfaces;

import java.util.List;

public interface IDispatcher {
    void receiveEvent(IEvent event, int turnsRemaining);

    void dispatchUnits(List<IEmergencyUnit> units);

    public void addUnit(IEmergencyUnit unit);

    public void addHospital(IHospital hospital);

    void sendReport();

    void updateTurns();
}
