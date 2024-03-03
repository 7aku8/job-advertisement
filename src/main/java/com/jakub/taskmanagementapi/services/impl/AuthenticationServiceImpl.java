package com.jakub.taskmanagementapi.services.impl;

import com.jakub.taskmanagementapi.auth.JwtUtil;
import com.jakub.taskmanagementapi.dto.request.LoginReq;
import com.jakub.taskmanagementapi.models.Role;
import com.jakub.taskmanagementapi.models.User;
import com.jakub.taskmanagementapi.models.enums.RoleName;
import com.jakub.taskmanagementapi.repositories.RoleRepository;
import com.jakub.taskmanagementapi.repositories.UserRepository;
import com.jakub.taskmanagementapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginReq loginReq) {
        try {
            logger.info("Attempting authentication for user: {}", loginReq.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword())
            );

            String email = authentication.getName();
            User user = new User();
            user.setEmail(email);

            String token = jwtUtil.createToken(user);

            logger.info("Authentication successful for user: {}", loginReq.getEmail());

            return token;
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}. Error: {}", loginReq.getEmail(), e.getMessage(), e);
            throw new RuntimeException("Authentication failed", e);
        }
    }

    @Transactional
    @Override
    public void register(User user, Boolean isAdmin) {
        try {
            if (userRepository == null || roleRepository == null) {
                throw new IllegalStateException("userRepository or roleRepository is null");
            }

            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

            if (existingUser.isPresent()) {
                throw new RuntimeException("User with this email already exists");
            }

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            Role role;
            if (isAdmin) {
                role = roleRepository.findByName(RoleName.ADMIN);
            } else {
                role = roleRepository.findByName(RoleName.USER);
            }

            if (role == null) {
                throw new IllegalStateException("Role not found");
            }

            user.setRoles(Set.of(role));
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error creating user: " + e.getMessage(), e);
            throw new RuntimeException("Error creating user: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error creating user", e);
            logger.debug(user.toString());
            throw new RuntimeException("Unexpected error creating user");
        }
    }
}
