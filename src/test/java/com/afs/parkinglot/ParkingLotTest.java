package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_given_parking_lot_and_car_when_parking(){
        //Gavin
        ParkingLot parkingLot = new ParkingLot(20);
        Car car = new Car();
        //When
        parkingLot.parking(car);
        //Then
        assertEquals(1, parkingLot.getParkedCars().size());
    }

    @Test
    public void should_return_car_given_parking_lot_and_car_when_without_position(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car = new Car();
        Ticket ticket = parkingLot.parking(car);
        //When and Then
        assertEquals(car, parkingLot.fetch(car, ticket));
    }

    @Test
    public void should_throw_exception_given_parking_lot_and_car_when_without_position(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.parking(car1);
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.parking(car2);
        });
        assertEquals("No enough position.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_given_parking_lot_and_car_when_without_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Ticket ticket = null;
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(car1, ticket);
        });
        assertEquals("No parking ticket.", exception.getMessage());
    }
}
