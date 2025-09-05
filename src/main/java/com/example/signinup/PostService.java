package com.example.signinup;

import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

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

    public ModelAndView postList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("posts", boardRepository.findAll());
        mav.setViewName("PostList");
        return mav;
    }

    public ModelAndView getPostDetail(Long id){
        ModelAndView mav = new ModelAndView();
        BoardEntity post = boardRepository.findById(id)
                .orElseThrow(() -> (new IllegalArgumentException("Invalid post Id:" + id)));
        if(post.isUse() == false){
            mav.setViewName("postNotFound");
            mav.addObject("message", "삭제된 게시글입니다.");
            return mav;
        }
        mav.addObject("post", post);
        mav.setViewName("PostDetail");
        return mav;
    }

    public ResponseEntity<String> deletePost(Long id, Principal principal) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        boardEntity.setUse(false);
        boardRepository.save(boardEntity);
        return ResponseEntity.ok("post deleted Successfully");
    }

    public String editPost(Long id, String title, String content) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
                board.setTitle(title);
                board.setContent(content);
        return "redirect:/";
    }

    public ModelAndView editPostPage(Long id) {
        ModelAndView mav = new ModelAndView();
        BoardEntity post = boardRepository.findById(id)
                .orElseThrow(() -> (new IllegalArgumentException("Invalid post Id:" + id)));
        if(post.isUse() == false){
            mav.setViewName("postNotFound");
            mav.addObject("message", "삭제된 게시글입니다.");
            return mav;
        }
        mav.addObject("post", post);
        mav.setViewName("EditPost");
        return mav;
    }
}
