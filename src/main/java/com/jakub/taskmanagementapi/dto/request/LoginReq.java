package com.jakub.taskmanagementapi.dto.request;

import jakarta.validation.constraints.Email;
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
public class LoginReq {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Size(min = 6, max = 50, message = "Length must be between 6 and 50.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 50, message = "Length must be between 6 and 50.")
    private String password;
}
