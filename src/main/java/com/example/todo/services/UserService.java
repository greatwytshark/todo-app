package com.example.todo.services;

import com.example.todo.domain.User;

public interface UserService  {
    User registerUser(String firstName, String lastName, String email, String username, String password);
}
