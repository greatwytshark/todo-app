package com.example.todo.controllers;

import com.example.todo.domain.User;
import com.example.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public record UserRecord(String firstName, String lastName, String email, String username, String password){}

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRecord userRecord){
        User user = userService.registerUser(userRecord);
        return user;
    };
}
