package com.example.todo.services.implementations;

import com.example.todo.controllers.UserController;
import com.example.todo.domain.User;
import com.example.todo.exceptions.SystemException;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

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

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication)) {
            throw new SystemException("User not authenticated");
        }

        return authentication.getName();
    }

}
