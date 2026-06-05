package com.ecom.userservice.entity;

import com.ecom.userservice.dto.UserDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document("user")
public class User {

    @Id
    private String id;

    @NotBlank(message = "username cannot be blank")
    private String userName;

    @Size(min = 6, max = 32, message = "password must be between 6 and 32 characters")
    private String password;

    @Email(message = "invalid email format")
    private String emailId;

    private String firstName;
    private String lastName;
    private Set<String> roles = new HashSet<>();

    public static User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmailId(userDto.getEmailId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getRoles() != null) {
            user.setRoles(userDto.getRoles());
        }
        return user;
    }

    public static UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmailId(user.getEmailId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}



