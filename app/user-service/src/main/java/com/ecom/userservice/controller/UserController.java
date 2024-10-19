package com.ecom.userservice.controller;

import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
//@Validated
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("adduser")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDto userDto) {
        log.info("request received for adding user with username :"+userDto.getUserName());
        String message = userService.addUser(userDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("getuser")
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        log.info("request received for fetching user details with Id :"+id);
        UserDto userDto = userService.getUser(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PutMapping("updateuser")
    public ResponseEntity<String> updateUser(UserDto userDto) {
        log.info("request received for updating user details for user :"+userDto.getUserName());
        String message = userService.updateUser(userDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping("deleteuser")
    public ResponseEntity<String> deleteUser(@RequestParam String id) {
        log.info("request received for deleting user id :"+id);
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
