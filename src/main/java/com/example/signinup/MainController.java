package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String main(Model model,  Principal principal) {
        String email = principal.getName();
        model.addAttribute("email", email);
        return "main";
    }



}
