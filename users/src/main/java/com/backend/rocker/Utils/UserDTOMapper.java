package com.backend.rocker.Utils;

import com.backend.rocker.dtos.LoginDTO;
import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    private final PasswordEncoder passwordEncoder ;

    public UserDTOMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO convertUserToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getId());
        return userDTO;
    }

    public User convertDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    public UserDTO convertUserToDto(User user,String jwtToken){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getId());
        userDTO.setJwtToken(jwtToken);
        return userDTO;
    }

    public LoginDTO convertUserToLoginDto(User user, String jwtToken){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(user.getEmail());
        loginDTO.setJwtToken(jwtToken);
        return loginDTO;
    }
}
