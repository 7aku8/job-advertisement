package com.jakub.taskmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateJobRes {
    private String jobId;
    private String message;
}
