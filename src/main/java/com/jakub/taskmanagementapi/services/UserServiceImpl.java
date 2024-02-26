package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.models.User;
import com.jakub.taskmanagementapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        // Checking if user with this email already exists
        int sameUsers = userRepository.countByEmail(user.getEmail());
        if (sameUsers > 0) {
            // @TODO: change this to a custom exception
            throw new IllegalArgumentException("User with this email already exists");
        }

        // Validating password strength
        if (!isPasswordStrong(user.getPassword())) {
            // @TODO: change this to a custom exception
            throw new IllegalArgumentException("Password is too weak. It should contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.");
        }

        userRepository.save(user);
    }

    private boolean isPasswordStrong(String password) {
        // Check if the password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!password.matches(".*[!@#$%^&*].*")) {
            return false;
        }

        // Check if the password is at least 8 characters long
        return password.length() >= 8;
    }
}
