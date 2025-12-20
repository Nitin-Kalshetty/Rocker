package com.backend.rocker.security;

import com.backend.rocker.Utils.UserDTOMapper;
import com.backend.rocker.dtos.LoginDTO;
import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserDTOMapper userDTOMapper;

    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,JwtService jwtService,UserDTOMapper userDTOMapper){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDTOMapper = userDTOMapper;
    }


    public LoginDTO login(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateJwtAccessToken(user);

        return userDTOMapper.convertUserToLoginDto(user,jwtToken);

    }


}
