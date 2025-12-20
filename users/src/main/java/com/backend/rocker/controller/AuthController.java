package com.backend.rocker.controller;

import com.backend.rocker.dtos.LoginDTO;
import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.security.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody @Valid LoginDTO requestDTO){
        logger.info("Triggered Login User Endpoint");
        return new ResponseEntity<>(authService.login(requestDTO), HttpStatus.CREATED);
    }
}
