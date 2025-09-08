package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    @Test
    public void should_return_ticket_when_parking_given_parking_lot_has_available_position() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(3, 1);
        ParkingLot parkingLot2 = new ParkingLot(6, 2);
        ParkingLot parkingLot3 = new ParkingLot(9, 3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);
        Car car = new Car();
        //when
        Map<Ticket, Integer> ticketMap = smartParkingBoy.parking(car);
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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car = new Car();
        smartParkingBoy.parking(car1);
        smartParkingBoy.parking(car2);
        //when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            smartParkingBoy.parking(car);
        });
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}