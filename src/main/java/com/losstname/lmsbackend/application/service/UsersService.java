package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.user.Users;
import com.losstname.lmsbackend.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class UsersService {

    private final UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users createUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public Users updateUser(Long id, Users usersDetails) {
        Users users = userRepository.findById(id).orElse(null);
        if (users != null) {
            users.setUsername(usersDetails.getUsername());
            users.setEmail(usersDetails.getEmail());
            users.setPassword(usersDetails.getPassword());
            users.setRole(usersDetails.getRole());
            return userRepository.save(users);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
