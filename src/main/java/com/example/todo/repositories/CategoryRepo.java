package com.example.todo.repositories;

import com.example.todo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByUsers_Id(Long userId);
    Set<Category> findAllById(Long categoryId);
}
