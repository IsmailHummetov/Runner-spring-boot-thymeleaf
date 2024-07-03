package com.example.Runner.service.inter;

import com.example.Runner.dto.RegistrationDto;
import com.example.Runner.models.User;

public interface UserService {
    void registerUser(RegistrationDto registrationDto);
    boolean existUserByUsername(String username);
    boolean existUserByEmail(String email);
    User findUserByUsername (String username);
}
