package com.example.signinup;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class PostController {

    PostService postService;

    @GetMapping("/board")
    public ModelAndView postList() {
        return postService.postList();
    }

    @GetMapping("/newPost")
    public ModelAndView newPost() {
        return postService.newPost();
    }

    @PostMapping("/newPost")
    public String makeNewPost(@RequestParam String title, @RequestParam String content, Principal principal) {
        return postService.makeNewPost(title,content,principal);
    }

    @GetMapping("/EditPost")
    public ModelAndView editPostPage(@RequestParam Long id) {
        return postService.editPostPage(id);
    }

    @Transactional
    @PostMapping("/EditPost")
    public String editPost(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        return postService.editPost(id, title,content);
    }

    @GetMapping("/board/post/{id}")
    public ModelAndView getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

    @DeleteMapping("/board/post/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> deletePost(@PathVariable Long id, Principal principal) {
        return postService.deletePost(id,principal);
    }
}
