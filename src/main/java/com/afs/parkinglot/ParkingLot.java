package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> parkedCars = new HashMap<Ticket, Car>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket parking(Car car){
        if(parkedCars.size() >= capacity) {
            throw new RuntimeException("No enough position.");
        }
        Ticket ticket = new Ticket(false);
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Car car, Ticket ticket){
        if(ticket == null) {
            throw new RuntimeException("No parking ticket.");
        }
        if(ticket.isUsed()) {
            throw new RuntimeException("Used parking ticket.");
        }
        if(!parkedCars.containsKey(ticket)) {
            throw new RuntimeException("Unrecognized parking ticket.");
        }
        if(!parkedCars.get(ticket).equals(car)) {
            throw new RuntimeException("Wrong parking ticket.");
        }
        ticket.setUsed(true);
        return parkedCars.remove(ticket);
    }

    public Map<Ticket, Car> getParkedCars() {
        return parkedCars;
    }
}
