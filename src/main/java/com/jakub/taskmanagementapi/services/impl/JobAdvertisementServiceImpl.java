package com.jakub.taskmanagementapi.services.impl;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import com.jakub.taskmanagementapi.repositories.JobAdvertisementRepository;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobAdvertisementServiceImpl implements JobAdvertisementService {
    private final JobAdvertisementRepository taskRepository;

    @Override
    public JobAdvertisement createTask(JobAdvertisement task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            //log error
            System.out.println("Error creating task");
            System.out.print(e.getMessage());
            throw new RuntimeException("Error creating task");
        }
    }

    @Override
    public void updateTask(UUID id, String title, String description, String email) {

    }

    @Override
    public void updateTaskStatus(UUID id, String title, String email) {

    }

    @Override
    public void deleteTask(UUID id) {

    }

    @Override
    public JobAdvertisement getTask(UUID id) {
        try {
            return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error getting task");
        }
    }

    @Override
    public void getUserTasks(UUID userId) {

    }
}
