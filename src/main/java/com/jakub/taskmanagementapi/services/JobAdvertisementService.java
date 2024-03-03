package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.models.JobAdvertisement;

import java.util.Set;
import java.util.UUID;

public interface JobAdvertisementService {
    JobAdvertisementDto create(JobAdvertisement task, String userEmail);

    Set<JobAdvertisementDto> get(String userEmail);

    JobAdvertisementDto getById(UUID id, String userEmail);

    void delete(UUID id, String userEmail);

    Set<JobAdvertisementDto> getWaitingForReview();

    JobAdvertisementDto approve(UUID id, String userEmail);

    JobAdvertisementDto reject(UUID id, String userEmail);

    Set<JobAdvertisementDto> getPublished();
}
