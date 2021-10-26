package com.proiect.travel.agency.entity.auth;

import com.proiect.travel.agency.dto.LoginDto;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "LoginModel")
public class LoginModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;
    @NotNull
    private String password;

    public LoginDto toLoginDto(){
        LoginDto loginDto = new LoginDto();
        loginDto.setId(this.id);
        loginDto.setEmail(this.email);
        loginDto.setPassword(this.password);

        return loginDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
