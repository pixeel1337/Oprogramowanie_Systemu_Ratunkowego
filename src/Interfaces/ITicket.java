package Interfaces;

public interface ITicket {
    IEvent getEvent();

    boolean isFinished();

    int geTurnsRemaining();

    void addHelpedVictims();

    int getAmountOfHelpedVictims();

    void processTurn();
}
