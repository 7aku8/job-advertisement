package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.models.JobAdvertisement;

import java.util.UUID;

public interface JobAdvertisementService {
    JobAdvertisement createTask(JobAdvertisement task);

    void updateTask(UUID id, String title, String description, String email);

    void updateTaskStatus(UUID id, String title, String email);

    void deleteTask(UUID id);

    JobAdvertisement getTask(UUID id);

    void getUserTasks(UUID userId);
}
