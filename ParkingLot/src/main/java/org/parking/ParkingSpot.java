package org.parking;

import org.parking.vehicle.Vehicle;
import org.parking.vehicle.VehicleType;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        if(isAvailable() && vehicleType == vehicle.getVehicleType()) {
            parkedVehicle = vehicle;
            return true;
        }
        else {
            System.out.println("Invalid vehicle type or spot already occupied.");
            return false;
        }
    }

    public synchronized void unparkVehicle() {
        parkedVehicle = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
