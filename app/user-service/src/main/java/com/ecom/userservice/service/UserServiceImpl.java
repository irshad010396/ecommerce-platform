package com.ecom.userservice.service;

import com.ecom.userservice.dto.AuthenticationRequest;
import com.ecom.userservice.dto.AuthenticationResponse;
import com.ecom.userservice.dto.RefreshTokenRequest;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.exception.UserAlreadyExistsException;
import com.ecom.userservice.exception.UserNotFoundException;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.security.JwtService;
import com.ecom.userservice.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username or password");
        }

        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new BadCredentialsException("Refresh token is invalid or expired");
        }

        String username = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found for refresh token"));

        String accessToken = jwtService.generateAccessToken(user);
        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public String registerUser(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("User payload cannot be null");
        }
        if (userRepository.existsByUserName(userDto.getUserName())) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        User user = User.convertUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles() == null || userDto.getRoles().isEmpty()
                ? Set.of(UserRole.ROLE_USER.name())
                : userDto.getRoles());

        userRepository.save(user);
        return "User successfully registered with id: " + user.getId();
    }

    @Override
    public String deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User can not be found"));
        userRepository.deleteById(id);
        return "User with id: " + id + " has been deleted";
    }

    @Override
    public String updateUser(UserDto userDto) {
        User existing = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("User can not be found"));

        existing.setFirstName(userDto.getFirstName());
        existing.setLastName(userDto.getLastName());
        existing.setEmailId(userDto.getEmailId());
        existing.setRoles(userDto.getRoles() != null && !userDto.getRoles().isEmpty()
                ? userDto.getRoles()
                : existing.getRoles());
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        userRepository.save(existing);
        return "User with id: " + existing.getId() + " has been updated";
    }

    @Override
    public UserDto getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User can not be found"));
        return User.convertUserToUserDto(user);
    }
}
