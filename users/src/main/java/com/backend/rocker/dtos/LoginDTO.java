package com.backend.rocker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class LoginDTO {

    @NotBlank(message = "Username is required")
    @Length(max = 20,message = "Username can't exceed 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String jwtToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
