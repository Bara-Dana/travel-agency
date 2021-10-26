package com.proiect.travel.agency.entity.auth;

import com.proiect.travel.agency.dto.UserDto;

public class ForgotPasswordModel {
    private UserDto email;

    public UserDto getEmail() {
        return email;
    }

    public void setEmail(UserDto email) {
        this.email = email;
    }
}
