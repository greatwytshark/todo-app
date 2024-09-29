package com.example.todo.services;

import com.example.todo.controllers.UserController;
import com.example.todo.domain.User;

public interface UserService  {
    User registerUser(UserController.UserRecord userRecord);
}
