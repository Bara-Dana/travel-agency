package com.proiect.travel.agency.entity;

import com.proiect.travel.agency.dto.OfferDto;
import com.proiect.travel.agency.dto.UserDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(mappedBy = "customers")
    private List<OfferModel> offers;


    public UserDto toUserDto(){
        UserDto userDto= new UserDto();
        userDto.setId(this.id);
        userDto.setUserName(this.username);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setEmail(this.email);
        userDto.setPassword(this.password);

        return userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


