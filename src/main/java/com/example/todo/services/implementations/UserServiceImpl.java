package com.example.todo.services.implementations;

import com.example.todo.controllers.UserController;
import com.example.todo.domain.User;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserController.UserRecord userRecord) {
        User user = new User();
        user.setFirstName(userRecord.firstName());
        user.setLastName(userRecord.lastName());
        user.setEmail(userRecord.email());
        user.setUsername(userRecord.username());
        user.setPassword(passwordEncoder.encode(userRecord.password()));

        return userRepo.save(user);
    }

}
