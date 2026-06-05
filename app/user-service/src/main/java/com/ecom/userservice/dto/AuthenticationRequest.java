package com.ecom.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotBlank(message = "username must be provided")
    private String userName;

    @NotBlank(message = "password must be provided")
    private String password;
}
