package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StandardParkingBoy extends ParkingBoy {

    List<ParkingLot> parkingLots = new ArrayList<>();

    public StandardParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots[0]);
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Map<Ticket, Integer> parking(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkedCars().size() < parkingLot.getCapacity()) {
                Ticket ticket = new Ticket(false);
                parkingLot.getParkedCars().put(ticket, car);
                return Map.of(ticket, parkingLot.getParkingLotId());
            }
        }
        throw new RuntimeException("No available position.");
    }

}
