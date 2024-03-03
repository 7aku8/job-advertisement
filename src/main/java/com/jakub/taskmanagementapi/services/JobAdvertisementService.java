package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.models.JobAdvertisement;

import java.util.Set;
import java.util.UUID;

public interface JobAdvertisementService {
    JobAdvertisementDto createTask(JobAdvertisement task, String userEmail);

    Set<JobAdvertisementDto> getTasks(String userEmail);

    JobAdvertisementDto getTask(UUID id, String userEmail);

}
