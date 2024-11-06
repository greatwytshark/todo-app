package com.example.todo.repositories;

import com.example.todo.domain.TodoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TodoCategoryRepo extends JpaRepository<TodoCategory, Long> {
    List<TodoCategory> findByUser_Id(Long userId);
    Set<TodoCategory> findAllById(Long categoryId);

    boolean existsByNameAndIdIsNotAndUser_Username(String name, Long id, String username);
}
