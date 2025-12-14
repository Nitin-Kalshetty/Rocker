package com.backend.rocker.security;

import com.backend.rocker.Utils.UserDTOMapper;
import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public UserDTO login(UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateJwtAccessToken(user);

        return UserDTOMapper.convertUserToDto(user,jwtToken);

    }

}
