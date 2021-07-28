package com.proiect.travel.agency.entity.auth;

import com.proiect.travel.agency.dto.UserDto;

public class ForgotPasswordModel {
    private String email;

    public UserDto getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
