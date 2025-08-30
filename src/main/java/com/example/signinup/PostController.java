package com.example.signinup;


import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class PostController {

    BoardRepository boardRepository;
    private final UserRepository userRepository;

    @GetMapping("/newPost")
    public ModelAndView newPost() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("newPost");
        return mav;
    }

    @PostMapping("/newPost")
    public String makeNewPost(@RequestParam String title, @RequestParam String content, Principal principal) {

        String userEmail = principal.getName();
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
        BoardEntity board = BoardEntity.builder()
                .title(title)
                .content(content)
                .user(user) // UserEntity 설정
                .isUse(true)
                .build();
        boardRepository.save(board);

        return "redirect:/";
    }

}
