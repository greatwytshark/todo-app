package com.example.todo.services;

import com.example.todo.controllers.AuthController;
import com.example.todo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthService {

    UserDTO loginUser(AuthController.LoginRequest request);
}
