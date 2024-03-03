package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.Role;
import com.jakub.taskmanagementapi.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(RoleName name);
}
