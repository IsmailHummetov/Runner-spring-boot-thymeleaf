package com.example.Runner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Please provide a username")
    private String username;
    @NotEmpty(message = "Please provide a valid email address")
    private String email;
    @Size(min = 6,message = "Password must be at least 6 characters long")
    private String password;
    @Size(min = 6,message = "Password must be at least 6 characters long")
    private String repeatPassword;

}
