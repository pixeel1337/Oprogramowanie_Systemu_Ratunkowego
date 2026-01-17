package Model;

import Interfaces.IPatient;


public class Patient implements IPatient {
    private String firstName;
    private String lastName;
    private String pesel;
    private String dateOfBirth;
    private int turnsToRecover = -1;
    private String Location;

    public Patient(String firstName, String lastName, String pesel, String dateOfBirth, int turnsToRecover) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.turnsToRecover = turnsToRecover;
        this.Location = "Miejsce Zdarzenia";
    }

    @Override
    public void startTreatment() {
        this.turnsToRecover = 2;
        this.Location = "Szpital";
    }

    @Override
    public String getFirstName(){
        return this.firstName;
    }
    @Override
    public boolean isPatientRecovered() {
        return this.turnsToRecover == 0;
    }

    @Override
    public void updateTurnsRemaining() {
        if(turnsToRecover > 0) {
            turnsToRecover--;
        }
    }

    @Override
    public String patientLocation() {
        return this.Location;
    }

    @Override
    public void setLocation(String location) {
        this.Location = location;
    }
}
