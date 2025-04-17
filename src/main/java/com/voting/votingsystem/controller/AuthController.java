package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.AppUser;
import com.voting.votingsystem.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Повертає сторінку login.html
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        repository.save(user);
        return "redirect:/login";
    }
}
