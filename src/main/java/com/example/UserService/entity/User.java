package com.example.UserService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotNull
    private String firstName;
    @Column(nullable = false)
    @NotNull
    private String lastName;
    @Column(nullable = false ,unique = true)
    @NotNull
    @Email
    private String email;
}

