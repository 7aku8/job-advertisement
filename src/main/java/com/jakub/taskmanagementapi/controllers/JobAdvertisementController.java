package com.jakub.taskmanagementapi.controllers;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import com.jakub.taskmanagementapi.services.JobAdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Validated
@RequestMapping("/api/v1/job-advertisements")
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    static Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/{jobAdvertisementId}")
    public ResponseEntity<JobAdvertisement> getTask(@PathVariable UUID jobAdvertisementId) {
        return new ResponseEntity<>(jobAdvertisementService.getTask(jobAdvertisementId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JobAdvertisement> createTask(@RequestBody JobAdvertisement task) {
        return new ResponseEntity<>(jobAdvertisementService.createTask(task), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
