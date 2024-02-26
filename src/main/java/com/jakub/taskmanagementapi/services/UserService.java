package com.jakub.taskmanagementapi.services;

public interface UserService {
    void registerUser(String firstName, String lastName, String email, String password);
}
