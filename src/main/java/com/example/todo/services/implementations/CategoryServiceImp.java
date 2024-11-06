package com.example.todo.services.implementations;

import com.example.todo.domain.TodoCategory;
import com.example.todo.domain.User;
import com.example.todo.dto.CategoryDTO;
import com.example.todo.dto.CreateCategoryRequest;
import com.example.todo.dto.UpdateCategoryRequest;
import com.example.todo.exceptions.DuplicateRecordException;
import com.example.todo.exceptions.RecordNotFoundException;
import com.example.todo.exceptions.SystemException;
import com.example.todo.repositories.TodoCategoryRepo;
import com.example.todo.repositories.UserRepo;
import com.example.todo.services.CategoryService;
import com.example.todo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final TodoCategoryRepo categoryRepo;

    private final UserRepo userRepo;

    private final UserService userService;


    @Override
    public List<CategoryDTO> getCategories(Long userId) {
        List<TodoCategory> todoCategories = categoryRepo.findByUser_Id(userId);
        List<CategoryDTO> categories = todoCategories.stream()
                .map(category -> new CategoryDTO(category.getName(), category.getDescription()))
                .collect(Collectors.toList());
        return categories;
    }

    @Override
    public void addCategory(CreateCategoryRequest category, Long userId, Principal principal) {

        User user =  userRepo.findById(userId)
                .orElseThrow(()-> new SystemException("User with id " + userId + " not found"));

        TodoCategory cat = new TodoCategory();
        cat.setName(category.name());
        cat.setDescription(category.description());
        cat.setUser(user);

        categoryRepo.save(cat);
    }

    @Override
    public void updateCategory(UpdateCategoryRequest updateCategoryRequest, Long categoryId, Principal principal) {

        String username = userService.getLoggedInUsername();

        TodoCategory cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Category with id: " + categoryId + " not found"));

        if(!Objects.equals(updateCategoryRequest.getName(), cat.getName())){

            boolean exists = categoryRepo.existsByNameAndIdIsNotAndUser_Username(updateCategoryRequest.getName(), categoryId, username);

            if(exists){
                throw new DuplicateRecordException("Category with name " + updateCategoryRequest.getName() + " already exists");
            }

        }

        cat.setName(updateCategoryRequest.getName());
        cat.setDescription(updateCategoryRequest.getDescription());
        categoryRepo.save(cat);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
