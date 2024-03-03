package com.jakub.taskmanagementapi.services.impl;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.models.JobAdvertisement;
import com.jakub.taskmanagementapi.models.User;
import com.jakub.taskmanagementapi.models.enums.AdvertisementStatus;
import com.jakub.taskmanagementapi.repositories.JobAdvertisementRepository;
import com.jakub.taskmanagementapi.repositories.UserRepository;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobAdvertisementServiceImpl implements JobAdvertisementService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final UserRepository userRepository;

    @Override
    public JobAdvertisementDto createTask(JobAdvertisement task, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

            task.setUser(user);

            // All tasks are created with status REVIEW
            task.setStatus(AdvertisementStatus.REVIEW);

            JobAdvertisement createdAdvertisement = jobAdvertisementRepository.save(task);

            return new JobAdvertisementDto(
                    createdAdvertisement.getId(),
                    createdAdvertisement.getTitle(),
                    createdAdvertisement.getDescription(),
                    createdAdvertisement.getStatus(),
                    createdAdvertisement.getCreatedAt()
            );
        } catch (Exception e) {
            System.out.println("Error creating task");
            System.out.print(e.getMessage());
            throw new RuntimeException("Error creating task");
        }
    }

    @Override
    public Set<JobAdvertisementDto> getTasks(String userEmail) {
        logger.info("Getting tasks for user: {}", userEmail);

        try {
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

            return user.getJobAdvertisements().stream()
                    .map(jobAdvertisement -> new JobAdvertisementDto(
                            jobAdvertisement.getId(),
                            jobAdvertisement.getTitle(),
                            jobAdvertisement.getDescription(),
                            jobAdvertisement.getStatus(),
                            jobAdvertisement.getCreatedAt()
                    ))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            logger.error("Error getting tasks for user: {}", userEmail);
            throw new RuntimeException("Error getting tasks");
        }
    }

    @Override
    public JobAdvertisementDto getTask(UUID id, String userEmail) {
        logger.info("Getting task with id: {}, and userEmail: {}", id.toString(), userEmail);

        try {
            JobAdvertisement job = jobAdvertisementRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

            if (!user.getEmail().equals(userEmail)) {
                throw new RuntimeException("Cannot get task for another user");
            }

            return new JobAdvertisementDto(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getStatus(),
                    job.getCreatedAt()
            );
        } catch (Exception e) {
            logger.error("Error getting task with id: {}", id);
            throw new RuntimeException("Error getting task");
        }
    }
}
