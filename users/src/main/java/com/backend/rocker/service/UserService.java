package com.backend.rocker.service;

import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;

public interface UserService {

    public UserDTO registerUser(UserDTO user);

    public UserDTO getUserByUserId(Long userId);

    public UserDTO getUserByPhone(String phoneNumber);

    public UserDTO getUserByEmail(String email);

    public UserDTO updateUser(UserDTO user);

    public UserDTO deleteUserById(Long userId);

    public UserDTO deleteUserByPhone(String phoneNumber);

    public UserDTO deleteUserByEmail(String email);

//    public UserDTO validateUserByUsernameAndPassword(String username,String password);

}
