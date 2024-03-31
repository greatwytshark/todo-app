package com.example.todo.services.implementations;

import com.example.todo.domain.Auth;
import com.example.todo.domain.User;
import com.example.todo.dto.UserDTO;
import com.example.todo.repositories.AuthRepo;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.AuthService;
import com.example.todo.services.TokenService;
import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String firstName, String secondName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        userRepo.save(user);
        return user;
    }

    @Override
    public void registerAuthDetails(User user, String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Auth auth = new Auth();
        auth.setUser(user);
        auth.setUsername(username);
        auth.setPassword(encodedPassword);
        authRepo.save(auth);
    }

    @Override
    public String loginUser(String username, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = tokenService.generateJwt(auth);
        return token;
    }
}
