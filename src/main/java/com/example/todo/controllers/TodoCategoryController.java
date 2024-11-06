package com.example.todo.controllers;

import com.example.todo.domain.TodoCategory;
import com.example.todo.dto.CategoryDTO;
import com.example.todo.dto.CreateCategoryRequest;
import com.example.todo.dto.UpdateCategoryRequest;
import com.example.todo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/category")
public class TodoCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{userId}/all")
    public List<CategoryDTO> getCategories(@PathVariable("userId") Long userId){
        return categoryService.getCategories(userId);
    }

    @PostMapping("/{userId}/add")
    public void addCategory(@RequestBody CreateCategoryRequest category, @PathVariable("userId") Long userId, Principal principal){
        categoryService.addCategory(category, userId, principal);
    }

    @PutMapping("/{categoryId}/update")
    public void updateCategory(@RequestBody UpdateCategoryRequest category, @PathVariable("categoryId") Long categoryId, Principal principal){
        categoryService.updateCategory(category, categoryId, principal);
    }

    @DeleteMapping("/{categoryId}/delete")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
