package com.jakub.taskmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RejectJobRes {
    private UUID jobId;
    private String message;
}
