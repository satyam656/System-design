package com.satyam.parkinggarage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ParkingGarageApplication {

	public static final Logger logger = LoggerFactory.getLogger(ParkingGarageApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Parking Garage Application");
		SpringApplication.run(ParkingGarageApplication.class, args);
	}
}
