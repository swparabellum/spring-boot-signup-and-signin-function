package com.example.signinup;


import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/board/post/{id}")
    public ModelAndView getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

}
