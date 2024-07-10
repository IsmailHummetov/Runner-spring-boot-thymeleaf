package com.example.Runner.controller;

import com.example.Runner.dto.RegistrationDto;
import com.example.Runner.service.inter.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth management APIs")
@RestController
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String createRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register/save")
    public String saveRegister(@Valid @ModelAttribute("user") RegistrationDto user,
                               BindingResult result,
                               Model model) {

        if (userService.existUserByUsername(user.getUsername())) {
            return "redirect:/register?usernameUsed";
        }
        else if (userService.existUserByEmail(user.getEmail())) {
            return "redirect:/register?emailUsed";
        }
        else if (user.getPassword() != null && user.getRepeatPassword() != null && !user.getPassword().equals(user.getRepeatPassword())) {
            return "redirect:/register?passwordWrong";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }

        userService.registerUser(user);
        return "redirect:/clubs?success";
    }

    @GetMapping("/login")
    public String createLoginForm() {
        return "login";
    }

}
