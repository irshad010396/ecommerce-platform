package com.ecom.userservice.service;

import com.ecom.userservice.dto.AuthenticationRequest;
import com.ecom.userservice.dto.AuthenticationResponse;
import com.ecom.userservice.dto.RefreshTokenRequest;
import com.ecom.userservice.dto.UserDto;

public interface UserService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    String registerUser(UserDto userDto);

    String deleteUser(String id);

    String updateUser(UserDto userDto);

    UserDto getUser(String id);
}
