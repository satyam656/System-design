package com.satyam.parkinggarage.service;

import com.satyam.parkinggarage.model.User;
import com.satyam.parkinggarage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.satyam.parkinggarage.ParkingGarageApplication.logger;

@Service
public class userServiceImpl implements userService {

    private UserRepository userRepository;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean createAccount(User user) {
        Optional<User> userInDb = userRepository.findByUserName(user.getUserName());

        if(userInDb.isPresent()) {
            logger.info("Username : {} already exists, Please use different username!", user.getUserName());
            return false;
        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);
        logger.info("User details : {}, {}, {}", newUser.getId(), newUser.getUserName(), newUser.getPassword());
        return true;
    }

    @Override
    public boolean logIn(User user) {
        Optional<User> userInDb = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        return userInDb.isPresent();
    }
}
