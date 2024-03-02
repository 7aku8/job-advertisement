package com.jakub.taskmanagementapi.dto.request;

import com.jakub.taskmanagementapi.models.User;
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
public class RegisterReq {
    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "Length must be between 2 and 50.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Length must be between 2 and 50.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Size(min = 6, max = 50, message = "Length must be between 6 and 50.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 50, message = "Length must be between 6 and 50.")
    private String password;

    private Boolean isAdmin;

    public User toUser() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
