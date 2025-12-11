package com.backend.rocker.controller;

import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.model.User;
import com.backend.rocker.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO requestDTO){
        logger.info("Triggered Register User Endpoint");
        return new ResponseEntity<>(userService.registerUser(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getByPhone/{phone}")
    public ResponseEntity<UserDTO> getUserByPhone(@PathVariable("phone") String phoneNumber) {
        return new ResponseEntity<>(userService.getUserByPhone(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByPhone/{phone}")
    public ResponseEntity<UserDTO> deleteUserByPhone(@PathVariable("phone") String phoneNumber) {
        return new ResponseEntity<>(userService.deleteUserByPhone(phoneNumber), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<UserDTO> deleteUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.deleteUserByEmail(email), HttpStatus.OK);
    }
}
