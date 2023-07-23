package com.mountblue.instagram.restcontroller;

import com.mountblue.instagram.model.Post;
import com.mountblue.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/getAllPost")
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
}
