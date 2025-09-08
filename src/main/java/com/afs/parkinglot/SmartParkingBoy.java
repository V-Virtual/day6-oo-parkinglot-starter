package com.afs.parkinglot;

import java.util.Map;

public class SmartParkingBoy extends StandardParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Map<Ticket, Integer> parking(Car car) {
        ParkingLot selectedParkingLot = null;
        int maxAvailablePosition = -1;
        for (ParkingLot parkingLot : parkingLots) {
            int availablePosition = parkingLot.getCapacity() - parkingLot.getParkedCars().size();
            if (availablePosition > maxAvailablePosition) {
                maxAvailablePosition = availablePosition;
                selectedParkingLot = parkingLot;
            }
        }
        if (selectedParkingLot != null && maxAvailablePosition > 0) {
            Ticket ticket = new Ticket(false);
            selectedParkingLot.getParkedCars().put(ticket, car);
            return Map.of(ticket, selectedParkingLot.getParkingLotId());
        }
        throw new RuntimeException("No available position.");
    }
}
