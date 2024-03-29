package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import com.jakub.taskmanagementapi.models.User;
import com.jakub.taskmanagementapi.models.enums.AdvertisementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, UUID> {
    Optional<JobAdvertisement> findByIdAndUser(UUID id, User user);

    Set<JobAdvertisement> findByStatus(AdvertisementStatus status);
}
