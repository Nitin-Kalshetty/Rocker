package com.backend.rocker.Utils;

import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    private static PasswordEncoder passwordEncoder ;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.setPasswordEncoder(passwordEncoder);
    }

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
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}
