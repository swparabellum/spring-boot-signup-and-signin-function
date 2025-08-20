package com.example.signinup;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MainService mainService;


    @GetMapping("/")
    public ModelAndView main(Model model, Principal principal) {
        return mainService.main(model,principal);
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return mainService.login();
    }

    @DeleteMapping("/api/users/{email}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable String email, Principal principal) {
        return mainService.deleteUser(email,principal);

    }
}
