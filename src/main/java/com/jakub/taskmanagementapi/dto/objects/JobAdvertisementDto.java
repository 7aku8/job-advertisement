package com.jakub.taskmanagementapi.dto.objects;

import com.jakub.taskmanagementapi.models.enums.AdvertisementStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {
    private UUID id;
    private String title;
    private String description;
    private AdvertisementStatus status;
    private Date createdAt;
}
