package com.example.todo.services;

import com.example.todo.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface AuthService {

    UserDTO loginUser(String username, String password);
}
