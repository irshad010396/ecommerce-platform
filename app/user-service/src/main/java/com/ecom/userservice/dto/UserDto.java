package com.ecom.userservice.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    @NotNull(message = "username cannot be null")
    private String userName;
    @Size(min = 6,max = 9,message = "password cannot be lass then 6 and more then 9")
    private String password;
    private String emailId;
    private String firstName;
    private String lastName;
}
