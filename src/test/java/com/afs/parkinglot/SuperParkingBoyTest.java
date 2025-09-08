package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SuperParkingBoyTest {

    @Test
    public void should_return_ticket_when_parking_given_parking_lot_has_available_position() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(3, 1);
        ParkingLot parkingLot2 = new ParkingLot(6, 2);
        ParkingLot parkingLot3 = new ParkingLot(9, 3);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2, parkingLot3);
        Car Car1 = new Car();
        Car Car2 = new Car();
        Car Car3 = new Car();
        superParkingBoy.parking(Car1);
        superParkingBoy.parking(Car2);
        superParkingBoy.parking(Car3);
        Car car = new Car();
        //when
        Map<Ticket, Integer> ticketMap = superParkingBoy.parking(car);
        //then
        assertFalse(ticketMap.isEmpty());
        assertNotNull(ticketMap.keySet().iterator().next());
        assertEquals(3, ticketMap.values().iterator().next());
    }

    @Test
    public void should_throw_exception_when_parking_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(1, 2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car = new Car();
        superParkingBoy.parking(car1);
        superParkingBoy.parking(car2);
        //when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            superParkingBoy.parking(car);
        });
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}