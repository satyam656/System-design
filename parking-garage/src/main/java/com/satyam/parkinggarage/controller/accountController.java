package com.satyam.parkinggarage.controller;

import com.satyam.parkinggarage.model.User;
import com.satyam.parkinggarage.service.userService;
import org.springframework.web.bind.annotation.*;

import static com.satyam.parkinggarage.ParkingGarageApplication.logger;

@RestController
@RequestMapping()
public class accountController {

    private final userService userService;

    public accountController(com.satyam.parkinggarage.service.userService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/logIn")
    public boolean logIn(@RequestBody User user) {
        logger.info("trying to logging in with user : {}, password : {}", user.getUserName(), user.getPassword());
        boolean isLoggedIn =  userService.logIn(user);
        if(isLoggedIn == true)
            logger.info("Login successful");
        else
            logger.info("Invalid credentials, login unsuccessful");

        return isLoggedIn;
    }

    @PostMapping(value = "/createAccount")
    public boolean createUser(@RequestBody User user) {
        logger.info("API for creating account with user : {}, password : {} called!", user.getUserName(), user.getPassword());
        return userService.createAccount(user);
    }
}
