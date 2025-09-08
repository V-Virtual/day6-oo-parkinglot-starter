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
            throw new RuntimeException("Not enough position.");
        }
        Ticket ticket = new Ticket();
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket){
        if(!parkedCars.containsKey(ticket)) {
            throw new RuntimeException("Unrecognized parking ticket.");
        }
        return parkedCars.remove(ticket);
    }

    public Map<Ticket, Car> getParkedCars() {
        return parkedCars;
    }
}
