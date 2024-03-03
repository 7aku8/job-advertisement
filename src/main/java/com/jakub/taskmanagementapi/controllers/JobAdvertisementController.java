package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.dto.request.CreateJobReq;
import com.jakub.taskmanagementapi.dto.response.CreateJobRes;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/job-advertisements")
@RequiredArgsConstructor
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<Iterable<JobAdvertisementDto>> getTasks(Principal principal) {
        return new ResponseEntity<>(jobAdvertisementService.getTasks(principal.getName()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{jobAdvertisementId}")
    public ResponseEntity<JobAdvertisementDto> getTask(
            Principal principal,
            @PathVariable UUID jobAdvertisementId
    ) {
        return new ResponseEntity<>(jobAdvertisementService.getTask(jobAdvertisementId, principal.getName()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<CreateJobRes> createTask(
            Principal principal,
            @Valid @RequestBody CreateJobReq createJobReq
    ) {
        JobAdvertisementDto createdJob = jobAdvertisementService.createTask(
                createJobReq.toJobAdvertisement(),
                principal.getName()
        );

        return new ResponseEntity<>(
                new CreateJobRes(createdJob.getId().toString(), "Job created successfully!"),
                HttpStatus.CREATED
        );
    }
}
