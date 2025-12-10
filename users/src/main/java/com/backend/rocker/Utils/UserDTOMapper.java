package com.backend.rocker.Utils;

import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;

public class UserDTOMapper {
    
    public static UserDTO convertUserToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getId());
        return userDTO;
    }

    public static User convertDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswod(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}
