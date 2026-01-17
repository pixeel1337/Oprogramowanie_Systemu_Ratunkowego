package Model;

import Interfaces.IHospital;
import Interfaces.IPatient;

import java.util.ArrayList;
import java.util.List;

public class CityHospital implements IHospital {
    private String name;
    private int capacity;
    private List<IPatient> patients;


    public CityHospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.patients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAvailableBeds() {
        return this.capacity - this.patients.size();
    }

    @Override
    public boolean admitPatient(IPatient patient) {
        if(getAvailableBeds() > 0){
            patients.add(patient);
            patient.setLocation(this.name);
            patient.startTreatment();
            return true;
        }
        return false;
    }

    @Override
    public void updateStatus() {
        for(IPatient patient : patients){
            patient.updateTurnsRemaining();
        }
        patients.removeIf(p -> {
            if (p.isPatientRecovered()) {
                p.setLocation("W domu"); // Lepiej niż null
                return true; // Usuń z listy
            }
            return false;
        });
    }
}
