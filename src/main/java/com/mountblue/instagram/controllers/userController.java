package com.mountblue.instagram.controllers;

import com.mountblue.instagram.model.User;
import com.mountblue.instagram.repository.UserRepository;
import com.mountblue.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "create-account";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.registerUser(user)) {
            return "login";
        }
        model.addAttribute("existingUser", "User Already Exists");
        return "create-account";
    }

    @PostMapping("/upload-pic")
    public String uploadPic(@RequestParam("profilePic") MultipartFile profilePic) throws IOException {
        User user = userRepository.findByUserName("Saurabh");
        user.setProfilePic(profilePic.getBytes());
        userRepository.save(user);
        return "edit-profile";
    }

    @PostMapping("/account/update")
    public String uploadBio(@RequestParam("bio") String bio,@RequestParam("gender") String gender,@RequestParam("accType") String accType ) throws IOException {
        User user = userRepository.findByUserName("Saurabh");
        user.setBio(bio);
        user.setGender(gender);
        user.setAccountType(accType);
        userRepository.save(user);
        return "redirect:/accounts/edit";
    }


}
