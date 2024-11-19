package com.example.digiSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.service.UserUtility;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserHandler {

    @Autowired
    private UserUtility userService;

    // Get all users in the system
    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a specific user by their ID
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Add a new user to the system
    @PostMapping("/add")
    public UserModel createUser(@RequestBody UserModel user) { 
        return userService.createUser(user);
    }

    // Update an existing user's information
    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Delete a user by their ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Get a user by their email (with role addition)
    @GetMapping("/details")
    public UserModel getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    // Handle user login and authentication
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
       
        System.out.println(email + password );
        // Authenticate the user with provided credentials
        UserModel user = userService.authenticateUser(email, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Generate a token for the authenticated user
        String token = generateToken(user);

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    // Generate a simple token containing the user's ID and email
    private String generateToken(UserModel user) {
        return ("userId=" + user.getId() + ", email=" + user.getEmail());
    }
}