package com.satyam.parkinggarage.service;

import com.satyam.parkinggarage.model.Garage;
import com.satyam.parkinggarage.repository.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.satyam.parkinggarage.ParkingGarageApplication.logger;

@Service
public class GarageServiceImpl implements GarageService{

    private GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }
    @Override
    public boolean addGarage(Garage garage) {
        Optional<Garage> garageInDb = garageRepository.findByGarageNameAndZipcode(garage.getGarageName(), garage.getZipcode());
        if(garageInDb.isPresent()) {
            logger.info("Garage with name : {} is already available in area with zipcode : {}", garage.getGarageName(), garage.getZipcode());
            return false;
        }
        garageRepository.save(garage);
        return true;
    }
}
