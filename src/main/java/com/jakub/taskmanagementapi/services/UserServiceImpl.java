package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String firstName, String lastName, String email, String password) {
        // Checking if user with this email already exists
        int sameUsers = userRepository.countByEmail(email);
        if (sameUsers > 0) {
            // @TODO: change this to a custom exception
            throw new IllegalArgumentException("User with this email already exists");
        }

        // Validating password strength
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})")) {
            // @TODO: change this to a custom exception
            throw new IllegalArgumentException("Password is too weak");
        }

        userRepository.create(firstName, lastName, email, password);
    }
}
