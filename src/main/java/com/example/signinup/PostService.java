package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Service
@AllArgsConstructor
public class PostService {

    BoardRepository boardRepository;
    private final UserRepository userRepository;

    public ModelAndView newPost(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("newPost");
        return mav;
    }

    public String makeNewPost(String title, String content, Principal principal) {

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
