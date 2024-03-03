package com.jakub.taskmanagementapi.dto.request;

import com.jakub.taskmanagementapi.models.JobAdvertisement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class CreateJobReq {
    @NotBlank(message = "Title is required.")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters long.")
    private String title;

    @NotBlank(message = "Description is required.")
    @Size(min = 2, max = 500, message = "Description must be between 2 and 500 characters long.")
    private String description;

    public JobAdvertisement toJobAdvertisement() {
        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setTitle(this.title);
        jobAdvertisement.setDescription(this.description);
        
        return jobAdvertisement;
    }
}
