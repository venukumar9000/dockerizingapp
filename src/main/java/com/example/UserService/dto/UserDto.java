package com.example.UserService.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
   @NotEmpty(message = "firstname must not be empty")
    private String firstName;
    @NotEmpty(message = "lastName must not be empty")
    private String lastName;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "email should be valid")
    private String email;
}
