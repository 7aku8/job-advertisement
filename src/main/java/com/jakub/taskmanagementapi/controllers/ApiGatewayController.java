package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.dto.objects.JobAdvertisementDto;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/gateway")
@RequiredArgsConstructor
public class ApiGatewayController {
    private final JobAdvertisementService jobAdvertisementService;

    @GetMapping("/jobs")
    public ResponseEntity<Iterable<JobAdvertisementDto>> getPublishedJobs() {
        return new ResponseEntity<>(jobAdvertisementService.getPublished(), HttpStatus.OK);
    }
}
