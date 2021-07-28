package com.proiect.travel.agency.service;


import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        UserModel userModel = userOptional.orElseThrow(() -> new RuntimeException("Username doesn't exist"));

        String name = userModel.getUsername();
        String encodedPassword = userModel.getPassword();
        String role = userModel.getRole();


        return new User(name, encodedPassword,
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}


