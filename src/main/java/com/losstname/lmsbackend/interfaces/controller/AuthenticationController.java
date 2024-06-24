package com.losstname.lmsbackend.interfaces.controller;

import com.losstname.lmsbackend.application.service.AuthenticationService;
import com.losstname.lmsbackend.domain.model.user.Users;
import com.losstname.lmsbackend.interfaces.dto.JwtTokenDto;
import com.losstname.lmsbackend.interfaces.dto.UserLoginDto;
import com.losstname.lmsbackend.interfaces.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        Users registeredUser = authenticationService.register(userRegistrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserLoginDto userLoginDto) {
        JwtTokenDto token = authenticationService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }
}
