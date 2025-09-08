package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    public void should_return_car_given_car_and_ticket_when_fetch(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car = new Car();
        Ticket ticket = parkingLot.parking(car);
        //When and Then
        assertEquals(car, parkingLot.fetch(car, ticket));
    }

    @Test
    public void should_throw_exception_given_car_and_ticket_when_without_position(){
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
    public void should_throw_exception_given_car_when_without_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = null;
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(car, ticket);
        });
        assertEquals("No parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_given_parking_car_and_ticket_when_ticket_not_contain(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car1 = new Car();
        parkingLot.parking(car1);
        Car car2 = new Car();
        Ticket ticket = new Ticket(false);
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(car2, ticket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_given_car_and_ticket_when_ticket_used(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car = new Car();
        Ticket ticket = parkingLot.parking(car);
        parkingLot.fetch(car, ticket);
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(car, ticket);
        });
        assertEquals("Used parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_given_car_and_ticket_when_ticket_not_belong_to_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car1 = new Car();
        Ticket ticket = parkingLot.parking(car1);
        Car car2 = new Car();
        //When and Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(car2, ticket);
        });
        assertEquals("Wrong parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_cars_given_multiple_cars_and_ticket_when_fetchCars(){
        //Given
        ParkingLot parkingLot = new ParkingLot(20);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.parking(car1);
        Ticket ticket2 = parkingLot.parking(car2);
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);
        List<Car> cars = Arrays.asList(car1, car2);
        //When
        List<Car> fetchedCars = parkingLot.fetchCars(cars, tickets);
        //Then
        assertEquals(2, fetchedCars.size());
        assertEquals(car1, fetchedCars.get(0));
        assertEquals(car2, fetchedCars.get(1));
    }
}
