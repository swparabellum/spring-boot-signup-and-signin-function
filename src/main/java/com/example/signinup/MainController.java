package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public ModelAndView main(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView();
        String email = principal.getName();
        mav.addObject("items",userRepository.findAll());
        mav.addObject("email", email);
        mav.setViewName("main");
        return mav;
    }



}
