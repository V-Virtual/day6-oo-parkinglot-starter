package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_parking_given_parking_lot_has_available_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.getParkingLot().parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetch_given_valid_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.getParkingLot().parking(car);
        //when
        Car fetchedCar = parkingBoy.getParkingLot().fetch(car, ticket);
        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_throw_exception_when_parking_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.getParkingLot().parking(car1);
        //when & then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingBoy.getParkingLot().parking(car2);
        });
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetch_given_without_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = null;
        //when & then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingBoy.getParkingLot().fetch(car, ticket);
        });
        assertEquals("No parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetch_given_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        parkingBoy.getParkingLot().parking(car1);
        Car car2 = new Car();
        Ticket ticket = new Ticket(false);
        //when & then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingBoy.getParkingLot().fetch(car2, ticket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.getParkingLot().parking(car);
        parkingBoy.getParkingLot().fetch(car, ticket);
        //when & then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingBoy.getParkingLot().fetch(car, ticket);
        });
        assertEquals("Used parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetch_given_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Ticket ticket1 = parkingBoy.getParkingLot().parking(car1);
        Car car2 = new Car();
        Ticket ticket2 = parkingBoy.getParkingLot().parking(car2);
        //when & then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingBoy.getParkingLot().fetch(car1, ticket2);
        });
        assertEquals("Wrong parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_cars_when_fetchCars_given_multiple_cars_and_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.getParkingLot().parking(car1);
        Ticket ticket2 = parkingBoy.getParkingLot().parking(car2);
        //when
        Car fetchedCar1 = parkingBoy.getParkingLot().fetch(car1, ticket1);
        Car fetchedCar2 = parkingBoy.getParkingLot().fetch(car2, ticket2);
        //then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }
}