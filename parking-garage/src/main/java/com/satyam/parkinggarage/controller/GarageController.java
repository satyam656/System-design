package com.satyam.parkinggarage.controller;

import com.satyam.parkinggarage.model.Garage;
import com.satyam.parkinggarage.service.GarageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.satyam.parkinggarage.ParkingGarageApplication.logger;

@RestController
@RequestMapping
public class GarageController {
    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping(value = "/addGarage")
    public boolean addGarage(@RequestBody Garage garage) {
        logger.info("Trying to add new garage with name : {}, zipcode : {} to the database", garage.getGarageName(), garage.getZipcode());
        boolean isGarageAdded = garageService.addGarage(garage);
        if(isGarageAdded)
            logger.info("Garage added successfully to the database");
        else
            logger.info("Garage couldn't be added");

        return isGarageAdded;
    }
}
