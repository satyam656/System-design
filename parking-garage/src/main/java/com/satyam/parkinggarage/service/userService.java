package com.satyam.parkinggarage.service;

import com.satyam.parkinggarage.model.User;

public interface userService {
    public boolean createAccount(User user);

    public boolean logIn(User user);
}
