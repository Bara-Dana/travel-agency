package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.entity.auth.ForgotPasswordModel;
import com.proiect.travel.agency.entity.auth.LoginModel;
import com.proiect.travel.agency.entity.auth.RegisterModel;
import com.proiect.travel.agency.entity.auth.Utils;
import com.proiect.travel.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    public AuthController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                          AuthenticationManager authenticationManager) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/auth/register")
    public ResponseEntity register(@RequestBody RegisterModel registerModel) {
        // ShowData from body request
        System.out.println(registerModel.getFirstName());
        System.out.println(registerModel.getLastName());
        System.out.println(registerModel.getEmail());

        UserModel userFromDB = this.userRepository.findByEmail(registerModel.getEmail()).orElse(null);
        if (userFromDB != null) {
            return new ResponseEntity("Acest email este deja inregistrat!", HttpStatus.BAD_REQUEST);
        }
        // Salvam parola primita intr-o variabila
        String currentPassword = registerModel.getPassword();
        // Criptam parola pentru a o salva in baza de date
        String newPassword = this.bCryptPasswordEncoder.encode(currentPassword);
        System.out.println(newPassword);
        UserModel user = new UserModel();
        user.setUsername(registerModel.getUsername());
        user.setEmail(registerModel.getEmail());
        user.setPassword(newPassword);
        user.setFirstName(registerModel.getFirstName());
        user.setLastName(registerModel.getLastName());
        user.setRole("ROLE_USER");
        // Salvam User-ul in baza de date
        this.userRepository.save(user);
        // Returnam raspuns catre client
        return new ResponseEntity("utilizator creat cu succes", HttpStatus.OK);
    }
//    @PostMapping("/auth/login")
//    public ResponseEntity loginWithToken(@RequestBody LoginModel loginModel) {
//        System.out.println(loginModel.getEmail());
//        System.out.println(loginModel.getPassword());
//        try {
//            Authentication authenticate = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
//            UserModel user = (UserModel) authenticate.getPrincipal();
//            // Pregatim obiectul pentru client
//            UserModel loginResponseModel = user; //jwtTokenUtil.generateAccessToken(user),
//            return new ResponseEntity(loginResponseModel, HttpStatus.OK);
//        } catch (BadCredentialsException ex) {
//            System.out.println("catch from loginWithToken");
//            return new ResponseEntity( ex.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel) {
        System.out.println(loginModel.getEmail());
        System.out.println(loginModel.getPassword());

        UserModel user = this.userRepository.findByEmail(loginModel.getEmail()).orElse(null);
        if (user != null) {
            if (this.bCryptPasswordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
                return new ResponseEntity(user, HttpStatus.OK);
            }
            return new ResponseEntity("Parola incorecta! ", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity( "Reincercati!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordModel forgotPasswordModel) {
        UserModel user = this.userRepository.findByEmail(forgotPasswordModel.getEmail()).orElse(null);
        if (user != null) {
            String generatedPassword = Utils.getRandomString(6);
            System.out.println(generatedPassword);
            String encodedPassword = this.bCryptPasswordEncoder.encode(generatedPassword);
            user.setPassword(encodedPassword);
            this.userRepository.save(user);
            return new ResponseEntity("Parola a fost schimbata cu succes: " + generatedPassword, HttpStatus.OK);
        } else {
            return new ResponseEntity("Nu am gasit utilizator", HttpStatus.BAD_REQUEST);
        }
    }
}
