package com.example.todo.services.implementations;

import com.example.todo.domain.User;
import com.example.todo.dto.UserDTO;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.AuthService;
import com.example.todo.services.TokenService;
import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Override
    public User registerUser(String firstName, String secondName, String username, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setUsername(username);
        user.setEmail(email);
        userRepo.save(user);
        return user;
    }

    @Override
    public UserDTO loginUser(String username, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = tokenService.generateJwt(auth);
        return new UserDTO("", "", username);
    }
}
