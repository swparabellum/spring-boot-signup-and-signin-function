package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class SignupController {

    private SignupService signupService;

    @GetMapping("/signup")
    public ModelAndView signup() {
        return signupService.signup();
    }

    @PostMapping("/signup")
    public String processSignup(SignupRequestDto signupRequestDto) {
        signupService.processSignup(signupRequestDto);
        return "redirect:/login";
    }

}
