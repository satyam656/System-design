package com.satyam.parkinggarage.repository;

import com.satyam.parkinggarage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from users U where U.userName = ?1", nativeQuery = true)
    public Optional<User> findByUserName(String username);

    @Query(value = "SELECT * FROM users U where U.userName = ?1 and U.password = ?2", nativeQuery = true)
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
