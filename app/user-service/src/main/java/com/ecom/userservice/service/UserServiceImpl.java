package com.ecom.userservice.service;

import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.exception.UserNotFoundException;
import com.ecom.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public String deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User can not be found"));
        userRepository.deleteById(id);
        return "user with userid :" +id+ "has been deleted";
    }

    @Override
    public String updateUser(UserDto userDto) {
        userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException("User can not be found"));
        User user = User.convertUserDtoToUser(userDto);
        userRepository.save(user);
        return "user with userid :" +user.getId() + "has been update";
    }

    @Override
    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User can not be found"));
        return User.convertUserToUserDto(user);
    }

    @Override
    public String addUser(UserDto userDto) {
        if (userDto==null) {
            throw new UserNotFoundException("User data can not be null.");
        }
        User user = User.convertUserDtoToUser(userDto);
        userRepository.save(user);
        return "User with user id :"+user.getId()+"has been added.";
    }
}
