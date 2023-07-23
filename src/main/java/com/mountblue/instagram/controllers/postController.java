package com.mountblue.instagram.controllers;

import com.mountblue.instagram.model.Post;
import com.mountblue.instagram.model.User;
import com.mountblue.instagram.repository.UserRepository;
import com.mountblue.instagram.service.PostService;


import com.mountblue.instagram.service.UserService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class postController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/publish-post")
    public String publishPost(@RequestParam("file") MultipartFile[] file , @ModelAttribute Post post, @RequestParam("tags") String tags) throws IOException {
        postService.publishPost(post, file, tags);
    return "redirect:/profile";
    }
    @GetMapping("/profile")
    public String profile(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);
        return "profile";
    }

    @GetMapping("/view/{id}")
    public void viewFile(@PathVariable("id") ObjectId id, Model model, HttpServletResponse response) throws IOException {
        Post post = postService.findFileByPostId(id);
        if (post != null) {
            ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(post.getContents().get(0));
            outputStream.close();
        }
    }

    @GetMapping("/all-view/{id}/{start}")
    public String allView(@PathVariable("id") ObjectId id , @PathVariable("start") Integer pageNumber, Model model){
        Post post = postService.findFileByPostId(id);

        model.addAttribute("post", post);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", post.getContents().size());
        return "views";
    }

    @GetMapping("/viewPic/{id}/{start}")
    public void viewPic(@PathVariable("id")  ObjectId id, @PathVariable("start") Integer pageNumber,Model model, HttpServletResponse response) throws IOException {
        Post post = postService.findFileByPostId(id);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(post.getContents().get(pageNumber));
            outputStream.close();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", post.getContents().size());

    }

    @GetMapping("/accounts/edit")
    public String editProfile(Model model){
        User user = userRepository.findByUserName("Saurabh");
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);
        return "home";
    }

}
