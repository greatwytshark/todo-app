package com.example.todo.services;

import com.example.todo.domain.User;
import com.example.todo.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User registerUser(String firstName, String secondName, String email);
    void registerAuthDetails(User user, String username, String password);
    String loginUser(String username, String password);
}
