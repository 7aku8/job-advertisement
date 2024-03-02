package com.jakub.taskmanagementapi.repositories;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, UUID> {
    JobAdvertisement findByTitle(String title);
}
