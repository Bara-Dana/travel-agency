package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.dto.UserDto;
import com.proiect.travel.agency.entity.auth.ForgotPasswordModel;
import com.proiect.travel.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                          AuthenticationManager authenticationManager) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto) {

        userService.registerUser(userDto);
        return new ResponseEntity("Utilizator creat cu succes", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) throws Exception {
      UserDto userLogin =  userService.loadUserByEmail(userDto);
        return new ResponseEntity(userLogin,HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordModel forgotPasswordModel) throws Exception {
        userService.loadUserByEmail(forgotPasswordModel.getEmail());
        return new ResponseEntity(HttpStatus.OK);
    }

}

