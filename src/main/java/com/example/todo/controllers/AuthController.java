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

    record createUser(String firstName, String secondName, String email){}
    record authDetails(String email, String username, String password){}
    record signIn(String username, String password){}

    @PostMapping("/register")
    public UserDTO register(@RequestBody createUser request){
        User user = authService.registerUser(request.firstName, request.secondName, request.email);

        //generate OTP
        //send OTP to email

        return new UserDTO(user.getFirstName(), user.getSecondName());
    };
    @PostMapping("/auth-dets")
    public void registerAuthDetails(@RequestBody authDetails details){
        User user = userRepo.findByEmail(details.email);
        authService.registerAuthDetails(user, details.username, details.password);
    }

    @PostMapping("/otplogin")
    public void loginWithOtp(){}

    @PostMapping("/login")
    public String login(@RequestBody signIn auth){
        return authService.loginUser(auth.username, auth.password);
    }
}
