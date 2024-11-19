package com.example.digiSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.repository.UserStorage;

import java.util.List;

@Service
public class UserUtility {

    @Autowired
    private UserStorage userRepository;

    // Retrieve all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by their ID
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Create a new user
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    // Update an existing user's details
    public UserModel updateUser(Long id, UserModel updatedUser) {
        UserModel existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        }
        return null;  // Return null if the user doesn't exist
    }
    
    // Retrieve a user by their email
    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // Authenticate a user by their email and password
    public UserModel authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // Delete a user by their ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}