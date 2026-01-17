package Interfaces;

import Enums.ServiceType;

import java.lang.classfile.Interfaces;
import java.util.List;
import java.util.Map;

public interface IEvent {
    String getEventName();

    Map<ServiceType, Integer> getRequiredUnits();

    List<IPatient> getVictims();

    int getPriority();

}
