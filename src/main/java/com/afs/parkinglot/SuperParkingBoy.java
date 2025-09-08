package com.afs.parkinglot;

import java.util.Map;

public class SuperParkingBoy extends StandardParkingBoy{
    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Map<Ticket, Integer> parking(Car car) {
        ParkingLot selectedParkingLot = null;
        double maxAvailablePositionRate = -1;
        for (ParkingLot parkingLot : parkingLots) {
            double availablePositionRate = (double)(parkingLot.getCapacity() - parkingLot.getParkedCars().size()) / parkingLot.getCapacity();
            if (availablePositionRate > maxAvailablePositionRate) {
                maxAvailablePositionRate = availablePositionRate;
                selectedParkingLot = parkingLot;
            }
        }
        if (selectedParkingLot != null && maxAvailablePositionRate > 0) {
            Ticket ticket = new Ticket(false);
            selectedParkingLot.getParkedCars().put(ticket, car);
            return Map.of(ticket, selectedParkingLot.getParkingLotId());
        }
        throw new RuntimeException("No available position.");
    }
}
