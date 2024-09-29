package com.example.todo.controllers;

import com.example.todo.domain.Category;
import com.example.todo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{userId}/all")
    public List<Category> getCategories(@PathVariable("userId") Long userId){
        return categoryService.getCategories(userId);
    }

    @PostMapping("/{userId}/add")
    public void addCategory(@RequestBody Category category, @PathVariable("userId") Long userId){
        categoryService.addCategory(category, userId);
    }

    @PutMapping("/{categoryId}/update")
    public void updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId){
        categoryService.updateCategory(category, categoryId);
    }

    @DeleteMapping("/{categoryId}/delete")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
