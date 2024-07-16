package com.ecom.userservice.service;

import com.ecom.userservice.dto.UserDto;

public interface UserService {
    String deleteUser(String id);

    String updateUser(UserDto userDto);

    UserDto getUser(String id);

    String addUser(UserDto userDto);
}
