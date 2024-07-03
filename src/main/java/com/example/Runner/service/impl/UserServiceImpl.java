package com.example.Runner.service.impl;

import com.example.Runner.dto.RegistrationDto;
import com.example.Runner.models.Role;
import com.example.Runner.models.User;
import com.example.Runner.repository.RoleRepository;
import com.example.Runner.repository.UserRepository;
import com.example.Runner.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        Role role = roleRepository.findByRole("USER");
        User user = User.builder()
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .roles(Arrays.asList(role))
                .build();
        userRepository.save(user);
    }

    @Override
    public boolean existUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

}
