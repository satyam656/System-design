package org.parking;

import org.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private final List<Level> levels;

    private ParkingLot() {
        this.levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if(parkingLotInstance == null) {
            parkingLotInstance = new ParkingLot();
        }
        return parkingLotInstance;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(Level level: levels){
            if(level.parkVehicle(vehicle) == true) {
                System.out.println("Vehicle parked successfully.");
                return true;
            }
        }
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for(Level level: levels) {
            if(level.unparkVehicle(vehicle) == true) {
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        for(Level level : levels) {
            level.displayAvailability();
        }
    }
}
