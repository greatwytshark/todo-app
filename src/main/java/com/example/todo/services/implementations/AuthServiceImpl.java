package com.example.todo.services.implementations;

import com.example.todo.dto.UserDTO;
import com.example.todo.services.AuthService;
import com.example.todo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @Override
    public UserDTO loginUser(String username, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = tokenService.generateJwt(auth);
        return new UserDTO(username, token);
    }
}
