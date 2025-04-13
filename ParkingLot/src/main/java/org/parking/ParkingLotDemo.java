package org.parking;

import org.parking.vehicle.MotorCycle;
import org.parking.vehicle.Truck;
import org.parking.vehicle.Vehicle;
import org.parking.vehicle.Car;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.addLevel(new Level(1, 10));
        parkingLot.addLevel(new Level(2, 8));

        Vehicle car = new Car("XYZW");
        Vehicle truck = new Truck("TRUCK");
        Vehicle motorcycle = new MotorCycle("MOTORCYCLE");

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        parkingLot.displayAvailability();

        parkingLot.unparkVehicle(car);
        parkingLot.displayAvailability();
    }
}