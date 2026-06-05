package com.ecom.userservice.controller;

import com.ecom.userservice.dto.ApiResponse;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable String id) {
        log.info("Request received for fetching user details with id={}", id);
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "User retrieved", userDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable String id, @Valid @RequestBody UserDto userDto) {
        log.info("Request received for updating user details for id={}", id);
        userDto.setId(id);
        String message = userService.updateUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", message, null));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id) {
        log.info("Request received for deleting user id={}", id);
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", message, null));
    }
}
