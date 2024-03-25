package com.example.todo.services;

import com.example.todo.domain.User;
import com.example.todo.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface AuthService {
    User registerUser(String firstName, String secondName, String username, String email);
    UserDTO loginUser(String username, String password);
}
