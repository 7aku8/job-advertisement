package com.jakub.taskmanagementapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    @Size(min = 2, max = 50, message = "Length must be between 2 and 50.")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 50, message = "Length must be between 2 and 50.")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Email should be valid.")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<JobAdvertisement> jobAdvertisements;

    @Column(name = "password")
    private String password;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private String updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private String createdAt;
}
