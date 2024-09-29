package com.example.todo.services;

import com.example.todo.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories(Long userId);

    void addCategory(Category category, Long userId);

    void updateCategory(Category category, Long categoryId);

    void deleteCategory(Long categoryId);
}
