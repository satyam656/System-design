package com.satyam.parkinggarage.repository;

import com.satyam.parkinggarage.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    @Query(value = "SELECT * from garage G where G.garageName = ?1 and G.zipcode = ?2", nativeQuery = true)
    Optional<Garage> findByGarageNameAndZipcode(String garageName, String zipcode);
}
