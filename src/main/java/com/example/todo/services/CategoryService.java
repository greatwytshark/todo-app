package com.example.todo.services;

import com.example.todo.domain.TodoCategory;
import com.example.todo.dto.CategoryDTO;
import com.example.todo.dto.CreateCategoryRequest;
import com.example.todo.dto.UpdateCategoryRequest;

import java.security.Principal;
import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories(Long userId);
    void addCategory(CreateCategoryRequest category, Long userId, Principal principal);
    void updateCategory(UpdateCategoryRequest updateCategoryRequest, Long categoryId, Principal principal);
    void deleteCategory(Long categoryId);
}
