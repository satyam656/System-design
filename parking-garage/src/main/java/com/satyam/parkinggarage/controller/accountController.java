package com.satyam.parkinggarage.controller;

import com.satyam.parkinggarage.model.User;
import com.satyam.parkinggarage.service.userService;
import org.springframework.web.bind.annotation.*;

import static com.satyam.parkinggarage.ParkingGarageApplication.logger;

@RestController
@RequestMapping("/users")
public class accountController {

    private final userService userService;

    public accountController(com.satyam.parkinggarage.service.userService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/id")
    public boolean findAccount(@RequestBody String requestBody) {
        return requestBody.contains("satyam");
    }

    @PostMapping(value = "/createAccount")
    public boolean createUser(@RequestBody User user) {
        logger.info("API for creating account with user : {}, password : {} called!", user.getUserName(), user.getPassword());
        return userService.createAccount(user);
//        return true;
    }
}
