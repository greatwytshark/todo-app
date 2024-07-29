package com.example.todo.services.implementations;

import com.example.todo.domain.Category;
import com.example.todo.domain.Todo;
import com.example.todo.repositories.CategoryRepo;
import com.example.todo.repositories.TodoRepo;
import com.example.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TodoServiceImp implements TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Todo> getTodos(Long categoryId) {
        return todoRepo.findByCategory_Id(categoryId);
    }

    @Override
    public void addTodo(Todo todo, Long categoryId) {
        Set<Category> category = categoryRepo.findAllById(categoryId);
        Todo newTodo = new Todo();
        newTodo.setName(todo.getName());
        newTodo.setCategory(category);
        todoRepo.save(newTodo);
    }

    @Override
    public void updateTodo(Todo todo, Long todoId) {
        Todo newTodo = todoRepo.findById(todoId).get();
        newTodo.setName(todo.getName());
        todoRepo.save(newTodo);
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepo.deleteById(todoId);
    }
}
