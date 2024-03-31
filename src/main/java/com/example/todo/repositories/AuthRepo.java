package com.example.todo.repositories;

import com.example.todo.domain.Auth;
import com.example.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<Auth, Long> {
    Auth findByUsername(String username);
}
