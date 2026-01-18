package Model;

import Enums.ServiceType;
import Interfaces.IEvent;
import Interfaces.IPatient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmergencyEvent implements IEvent {
    private String eventName;
    private Map<ServiceType, Integer> requiredUnits;
    private List<IPatient> victims;
    private int priority;

    public EmergencyEvent(String eventName, Map<ServiceType, Integer> requiredUnits, int priority) {
        this.eventName = eventName;
        this.requiredUnits = requiredUnits;
        this.victims = new ArrayList<>();
        this.priority = priority;
    }

    @Override
    public void addVictim(IPatient victim) {
        this.victims.add(victim);
    }

    @Override
    public String getEventName() {
        return this.eventName;
    }

    @Override
    public Map<ServiceType, Integer> getRequiredUnits() {
        return this.requiredUnits;
    }

    @Override
    public List<IPatient> getVictims() {
        return this.victims;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
}
