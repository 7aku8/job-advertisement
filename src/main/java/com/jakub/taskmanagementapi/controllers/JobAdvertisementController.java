package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/job-advertisements")
@RequiredArgsConstructor
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{jobAdvertisementId}")
    public ResponseEntity<JobAdvertisement> getTask(
            @PathVariable UUID jobAdvertisementId
    ) {
        return new ResponseEntity<>(jobAdvertisementService.getTask(jobAdvertisementId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JobAdvertisement> createTask(@Valid @RequestBody JobAdvertisement task) {
        return new ResponseEntity<>(jobAdvertisementService.createTask(task), HttpStatus.CREATED);
    }
}
