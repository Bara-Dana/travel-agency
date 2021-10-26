package com.proiect.travel.agency.controller;
import com.proiect.travel.agency.dto.UserDto;
import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;


    @PutMapping("/user/editUser")
    public ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
        userService.editUser(id, userDto);
        return new ResponseEntity( HttpStatus.OK);
    }

    @DeleteMapping("/user/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/user/getUsers")
    public ResponseEntity getUsers(){
        List<UserModel> users = userService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/user/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        UserModel user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
