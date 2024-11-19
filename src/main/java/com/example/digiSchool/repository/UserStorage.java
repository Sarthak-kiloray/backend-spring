package com.example.digiSchool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.digiSchool.entity.UserModel;

public interface UserStorage extends JpaRepository<UserModel, Long> {

    // Find a user by their email
    UserModel findByEmail(String email);

    // Find a user by their email and password
    UserModel findByEmailAndPassword(String email, String password);

    // Custom query to find users by their username using a native SQL query
    @Query(value = "SELECT * FROM user u WHERE u.username = :username", nativeQuery = true)
    List<UserModel> findByUsername(@Param("username") String username);
}