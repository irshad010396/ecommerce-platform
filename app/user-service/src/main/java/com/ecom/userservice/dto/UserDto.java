package com.ecom.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String id;

    @NotBlank(message = "username cannot be blank")
    private String userName;

    @Size(min = 6, max = 32, message = "password must be between 6 and 32 characters")
    private String password;

    @Email(message = "email must be valid")
    private String emailId;

    private String firstName;
    private String lastName;
    private Set<String> roles = new HashSet<>();
}
