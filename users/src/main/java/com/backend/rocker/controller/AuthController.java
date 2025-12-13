package com.backend.rocker.controller;

import com.backend.rocker.dtos.UserDTO;
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

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid UserDTO requestDTO){
        logger.info("Triggered Register User Endpoint");
        return new ResponseEntity<>(new UserDTO(), HttpStatus.CREATED);
    }
}
