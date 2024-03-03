package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
