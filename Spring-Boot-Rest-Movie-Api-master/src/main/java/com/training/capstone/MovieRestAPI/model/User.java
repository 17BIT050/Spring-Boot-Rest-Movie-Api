package com.training.capstone.MovieRestAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class User {

    @Id
    private String userId; // Combination of String
    private String username;
    @Email
    private String email;
    private String password;
    private String role; // e.g., "USER", "ADMIN"



    public User(){}

    // Getters and Setters


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

