package Interfaces;

public interface IHospital {
    String getName();

    int getAvailableBeds();

    boolean admitPatient(IPatient patient);

    void updateStatus();
}
