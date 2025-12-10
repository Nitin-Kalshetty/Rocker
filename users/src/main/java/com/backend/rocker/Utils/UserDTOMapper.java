package com.backend.rocker.Utils;

import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;

public class UserDTOMapper {
    
    public static UserDTO convertUserToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
