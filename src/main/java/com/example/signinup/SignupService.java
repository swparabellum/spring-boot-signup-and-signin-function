package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
@AllArgsConstructor
public class SignupService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signup");
        return mav;

    }

    public void processSignup(SignupRequestDto signupRequestDto) {
        userRepository.save(signupRequestDto.toUser(passwordEncoder));
    }

}
