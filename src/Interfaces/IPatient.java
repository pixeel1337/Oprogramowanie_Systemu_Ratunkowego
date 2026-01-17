package Interfaces;

public interface IPatient {
    void startTreatment();

    boolean isPatientRecovered();

    void updateTurnsRemaining();

    String patientLocation();

    void setLocation(String location);

    String getFirstName();

}
