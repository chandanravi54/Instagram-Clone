package com.mountblue.instagram.restcontroller;

import com.mountblue.instagram.model.User;
import com.mountblue.instagram.repository.UserRepository;
import com.mountblue.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserService userService;

    public UserRestController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>("User Saved Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User existUser) {
        User user = userRepository.findByUserName(existUser.getUserName());
        if (user != null) {
            userRepository.deleteByUserName(user.getUserName());
            return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User is not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            userService.updateUser(user);
            return new ResponseEntity<>("Post Has been updated", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Post with id is not present please create the new post", HttpStatus.NOT_FOUND);
        }
    }
}
