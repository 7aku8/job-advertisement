package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.dto.response.ApproveJobRes;
import com.jakub.taskmanagementapi.dto.response.RejectJobRes;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/job-admin")
@RequiredArgsConstructor
public class JobAdminController {
    private final JobAdvertisementService jobAdvertisementService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/waiting")
    public ResponseEntity<Iterable<JobAdvertisementDto>> getWaiting() {
        return new ResponseEntity<>(jobAdvertisementService.getWaitingForReview(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve/{jobAdvertisementId}")
    public ResponseEntity<ApproveJobRes> approve(
            Principal principal,
            @PathVariable UUID jobAdvertisementId
    ) {
        JobAdvertisementDto job = jobAdvertisementService.approve(jobAdvertisementId, principal.getName());

        return new ResponseEntity<>(new ApproveJobRes(job.getId(), "Job approved."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/reject/{jobAdvertisementId}")
    public ResponseEntity<RejectJobRes> reject(
            Principal principal,
            @PathVariable UUID jobAdvertisementId
    ) {
        JobAdvertisementDto job = jobAdvertisementService.reject(jobAdvertisementId, principal.getName());

        return new ResponseEntity<>(new RejectJobRes(job.getId(), "Job rejected."), HttpStatus.OK);
    }
}
