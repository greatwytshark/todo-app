package com.example.todo.controllers;

import com.example.todo.domain.User;
import com.example.todo.dto.UserDTO;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthService authService;

    record LoginRequest(String username, String password){}


    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequest auth){
        return authService.loginUser(auth.username, auth.password);
    }
}
