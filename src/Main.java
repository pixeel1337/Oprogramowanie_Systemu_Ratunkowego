import Enums.ServiceType;
import Interfaces.IEvent;
import Logic.EmergencyDispatcher;
import Model.CityHospital;
import Model.EmergencyEvent;
import Model.Patient;
import Units.Ambulance;
import Units.PoliceCar;
import Units.FireTruck;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmergencyDispatcher dispatcherNorth = new EmergencyDispatcher("REJON PÓŁNOC");
        dispatcherNorth.addHospital(new CityHospital("Szpital Północny", 10));
        dispatcherNorth.addUnit(new Ambulance("Karetka-P1"));
        dispatcherNorth.addUnit(new PoliceCar("Policja-P1"));

        EmergencyDispatcher dispatcherSouth = new EmergencyDispatcher("REJON POŁUDNIE");
        dispatcherSouth.addHospital(new CityHospital("Przychodnia Mała", 1));
        dispatcherSouth.addHospital(new CityHospital("Klinika Południowa", 2));

        dispatcherSouth.addUnit(new Ambulance("Karetka-S1"));
        dispatcherSouth.addUnit(new Ambulance("Karetka-S2"));
        dispatcherSouth.addUnit(new Ambulance("Karetka-S3"));
        dispatcherSouth.addUnit(new Ambulance("Karetka-S4"));
        dispatcherSouth.addUnit(new PoliceCar("Policja-S1"));

        System.out.println("========== INICJALIZACJA SYSTEMU DWÓCH REJONÓW ==========");

        Map<ServiceType, Integer> needsNorth = new HashMap<>();
        needsNorth.put(ServiceType.MEDICAL, 1);
        IEvent eventNorth = new EmergencyEvent("Stłuczka Północ", needsNorth, 3);
        eventNorth.addVictim(new Patient("Marek", "Z", "1", 2));
        dispatcherNorth.receiveEvent(eventNorth, 3);

        Map<ServiceType, Integer> needsSouth = new HashMap<>();
        needsSouth.put(ServiceType.MEDICAL, 4);
        needsSouth.put(ServiceType.POLICE, 1);
        IEvent disasterSouth = new EmergencyEvent("Wielki Karambol Południe", needsSouth, 10);
        disasterSouth.addVictim(new Patient("Pacjent", "1", "P1", 4));
        disasterSouth.addVictim(new Patient("Pacjent", "2", "P2", 4));
        disasterSouth.addVictim(new Patient("Pacjent", "3", "P3", 4));
        disasterSouth.addVictim(new Patient("Pacjent", "4", "P4", 4));
        dispatcherSouth.receiveEvent(disasterSouth, 10);

        for (int t = 0; t < 10; t++) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("          SYMULACJA - TURA NR " + t);
            System.out.println("=".repeat(60));

            System.out.println("\n--- " + dispatcherNorth + " ---");
            dispatcherNorth.updateTurns();
            dispatcherNorth.sendReport();

            System.out.println("\n" + ".".repeat(40));

            System.out.println("\n--- " + dispatcherSouth + " ---");
            dispatcherSouth.updateTurns();
            dispatcherSouth.sendReport();
        }

        System.out.println("\n========== KONIEC TESTU ==========");
    }
}