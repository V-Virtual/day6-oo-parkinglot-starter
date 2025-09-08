package com.afs.parkinglot;

public class Ticket {
    private boolean isUsed;

    public Ticket(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
