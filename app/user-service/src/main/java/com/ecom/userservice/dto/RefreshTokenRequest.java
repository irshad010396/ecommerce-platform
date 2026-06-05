package com.ecom.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank(message = "refresh token must be provided")
    private String refreshToken;
}
