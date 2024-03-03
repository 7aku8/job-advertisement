package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, UUID> {
}
