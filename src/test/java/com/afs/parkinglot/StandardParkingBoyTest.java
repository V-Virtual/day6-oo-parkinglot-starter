package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {

    @Test
    public void should_return_ticket_when_parking_given_parking_lot_has_available_position() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(5, 1);
        ParkingLot parkingLot2 = new ParkingLot(10, 2);
        ParkingLot parkingLot3 = new ParkingLot(15, 3);
        ParkingLot parkingLot4 = new ParkingLot(20, 4);
        ParkingLot parkingLot5 = new ParkingLot(25, 5);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2, parkingLot3, parkingLot4, parkingLot5);
        Car Car1 = new Car();
        Car Car2 = new Car();
        Car Car3 = new Car();
        Car Car4 = new Car();
        Car Car5 = new Car();
        standardParkingBoy.parking(Car1);
        standardParkingBoy.parking(Car2);
        standardParkingBoy.parking(Car3);
        standardParkingBoy.parking(Car4);
        standardParkingBoy.parking(Car5);
        Car car = new Car();
        //when
        Map<Ticket, Integer> ticketMap = standardParkingBoy.parking(car);
        //then
        assertFalse(ticketMap.isEmpty());
        assertNotNull(ticketMap.keySet().iterator().next());
        assertEquals(2, ticketMap.values().iterator().next());
    }

    @Test
    public void should_throw_exception_when_parking_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(1, 2);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        standardParkingBoy.parking(car1);
        standardParkingBoy.parking(car2);
        //when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            standardParkingBoy.parking(car3);
        });
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}