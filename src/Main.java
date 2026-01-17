import Enums.ServiceType;
import Interfaces.IDispatcher;
import Interfaces.IEmergencyUnit;
import Interfaces.IEvent;
import Interfaces.IHospital;
import Interfaces.IPatient;
import Interfaces.ITicket;

import Units.Ambulance;
import Units.FireTruck;
import Units.PoliceCar;

import Model.CityHospital;
import Model.EmergencyEvent;
import Model.Patient;
import Model.Ticket;
import Logic.EmergencyDispatcher;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        EmergencyDispatcher dispatcher = new EmergencyDispatcher("First Dispatcher");

        Ambulance firstAmbulance = new Ambulance("First Ambulance");
        Ambulance secondAmbulance = new Ambulance("Second Ambulance");
        Ambulance thirdAmbulance = new Ambulance("Third Ambulance");

        PoliceCar firstPoliceCar = new PoliceCar("First Police");
        PoliceCar secondPoliceCar = new PoliceCar("Second Police");

        FireTruck firstFireTruck = new FireTruck("First Fire Truck");
        FireTruck secondFireTruck = new FireTruck("Second Fire Truck");

        dispatcher.addHospital(new CityHospital("Szpital wojewodzki", 10));


        Map<ServiceType, Integer> requiredServices = new HashMap<ServiceType, Integer>();
        requiredServices.put(ServiceType.MEDICAL, 2);
        requiredServices.put(ServiceType.POLICE, 4);
        IEvent accident = new EmergencyEvent("Karambol na S7", requiredServices, 5);

        dispatcher.receiveEvent(accident, 5);

        for(int i = 0; i < 10; i++) {
            System.out.println("\n-----TURA NR " + i + "-----");
            dispatcher.updateTurns();
            dispatcher.sendReport();
        }


    }
}
