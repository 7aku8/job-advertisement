package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.Role;
import com.jakub.taskmanagementapi.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(RoleName name);
}
