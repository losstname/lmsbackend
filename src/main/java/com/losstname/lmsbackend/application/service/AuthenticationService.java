package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.user.Users;
import com.losstname.lmsbackend.infrastructure.security.JwtUtil;
import com.losstname.lmsbackend.interfaces.dto.JwtTokenDto;
import com.losstname.lmsbackend.interfaces.dto.UserLoginDto;
import com.losstname.lmsbackend.interfaces.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, UsersService usersService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(UserRegistrationDto userRegistrationDto) {
        Users user = new Users();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setRole(Users.Role.valueOf(userRegistrationDto.getRole().toUpperCase()));
        return usersService.createUser(user);
    }

    public JwtTokenDto login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtTokenDto(jwtUtil.generateToken(userDetails.getUsername()));
    }

}
