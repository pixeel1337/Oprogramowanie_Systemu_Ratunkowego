package Model;


import Interfaces.IEvent;
import Interfaces.ITicket;


public class Ticket implements ITicket {
    private IEvent event;
    private int turnsRemaining;
    private boolean finished;
    private int victimsHelped = 0;

    public Ticket(IEvent event, int turnsRemaining) {
        this.event = event;
        this.turnsRemaining = turnsRemaining;
        this.finished = false;
    }


    @Override
    public IEvent getEvent() {
        return this.event;
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public int geTurnsRemaining() {
        return this.turnsRemaining;
    }

    @Override
    public void addHelpedVictims(){
        victimsHelped++;
    }

    @Override
    public int getAmountOfHelpedVictims() {
        return this.victimsHelped;
    }

    @Override
    public void processTurn() {
        if(turnsRemaining > 0) {
        turnsRemaining--;
        }
        if (turnsRemaining == 0) {
            this.finished = true;
        }
    }
}
