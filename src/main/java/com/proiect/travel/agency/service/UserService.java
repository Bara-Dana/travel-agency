package com.proiect.travel.agency.service;

import com.proiect.travel.agency.dto.UserDto;
import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.entity.auth.Utils;
import com.proiect.travel.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;







    public void registerUser(UserDto userDto) {

        UserModel newUser = new UserModel();

        newUser.setUsername(userDto.getUserName());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole("ROLE_USER");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

        newUser.setPassword(encodedPassword);
        newUser.setRole("ROLE_USER");

        userRepository.save(newUser);
    }

    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<UserModel> userModelOptional = userRepository.findByUsername(currentPrincipalName);
        return userModelOptional.orElseThrow(() -> new IllegalArgumentException("No user found"));
    }

    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void editUser(Long id, UserDto userDto) throws Exception {

        Optional<UserModel> userModel = userRepository.findById(id);

        String password = userDto.getPassword();
        if (password != null && !password.trim().isEmpty()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            userDto.setPassword(encodedPassword);
        }
        UserModel user = userModel.orElseThrow(() -> new Exception("Userul cu id " + id + "nu exista"));

        user.setUsername(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
//        user.setRole("ROLE_USER");

        if (!userDto.getPassword().equals("")) {

            System.out.println(userDto.getPassword());

            String passwordCrypt = bCryptPasswordEncoder.encode(userDto.getPassword());
            user.setPassword(password);
        }

        userRepository.save(user);
    }

    public UserModel getUserById(Long id) {
        OfferModel offerModel = new OfferModel();
        List<UserModel> customers = offerModel.getCustomers();

        UserModel userModel = new UserModel();
        customers.add(userModel);
        offerModel.setCustomers(customers);
        return userRepository.findById(id).orElse(null);
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public UserDto loadUserByEmail(UserDto userDto) throws Exception {

        System.out.println("Ceva loger");
        String generatedPassword = Utils.getRandomString(6);
        System.out.println(generatedPassword);
        String encodedPassword = this.bCryptPasswordEncoder.encode(generatedPassword);
        userDto.setPassword(encodedPassword);

      Optional<UserModel> userModelOptional = userRepository.checkIfExistUserByEmail(userDto.getEmail());
        if (userModelOptional.isPresent()) {
            return userModelOptional.get().toUserDto();
        }else{
            throw new Exception("User nu exista!");
        }

    }
}

