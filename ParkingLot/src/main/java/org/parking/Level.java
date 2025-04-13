package org.parking;

import org.parking.vehicle.Vehicle;
import org.parking.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int floor;
    private final List<ParkingSpot> parkingSpotList;

    public Level(int floor, int numSpots) {
        this.floor = floor;
        parkingSpotList = new ArrayList<>();

        // Asign Spots in the ratio of 50:40:10 for bikes, cars, trucks
        int numBikes = (int) (numSpots * 0.5);
        int numCars = (int) (numSpots * 0.4);

        for(int i = 1; i <= numBikes; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.MOTORCYCLE));
        }

        for(int i = numBikes+1; i <= numBikes + numCars; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.CAR));
        }

        for(int i = numBikes + numCars + 1; i <= numSpots; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpotList) {
            if(parkingSpot.isAvailable() && parkingSpot.getVehicleType() == vehicle.getVehicleType()) {
                parkingSpot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpotList) {
            if(!parkingSpot.isAvailable() && parkingSpot.getParkedVehicle().equals(vehicle)) {
                parkingSpot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level " + floor + " Availability:");
        for (ParkingSpot spot : parkingSpotList) {
            System.out.println("Spot " + spot.getSpotNumber() + ": " + (spot.isAvailable() ? "Available For"  : "Occupied By ")+" "+spot.getVehicleType());
        }
    }
}
