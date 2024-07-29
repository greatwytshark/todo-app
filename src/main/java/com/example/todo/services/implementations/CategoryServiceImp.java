package com.example.todo.services.implementations;

import com.example.todo.domain.Category;
import com.example.todo.domain.User;
import com.example.todo.repositories.CategoryRepo;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Category> getCategories(Long userId) {
        return categoryRepo.findByUsers_Id(userId);
    }

    @Override
    public void addCategory(Category category, Long userId) {
        Set<User> user =  userRepo.findAllById(userId);

        Category cat = new Category();
        cat.setName(category.getName());
        cat.setDescription(category.getDescription());
        cat.setUsers(user);

        categoryRepo.save(cat);
    }

    @Override
    public void updateCategory(Category category, Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).get();
        cat.setName(category.getName());
        cat.setDescription(category.getDescription());
        categoryRepo.save(cat);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
