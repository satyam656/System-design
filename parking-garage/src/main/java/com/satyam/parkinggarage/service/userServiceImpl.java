package com.satyam.parkinggarage.service;

import com.satyam.parkinggarage.model.User;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Override
    public boolean createAccount(User user) {
        return false;
    }
}
