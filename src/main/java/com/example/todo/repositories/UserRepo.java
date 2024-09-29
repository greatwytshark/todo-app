package com.example.todo.repositories;

import com.example.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    Set<User> findAllById(Long userId);
}
