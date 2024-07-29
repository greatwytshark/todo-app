package com.example.todo.controllers;

import com.example.todo.domain.Todo;
import com.example.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/{categoryId}/all")
    public List<Todo> getTodos(@PathVariable("categoryId") Long categoryId){
        return todoService.getTodos(categoryId);
    }

    @PostMapping("/{categoryId}/add")
    public void addTodo(@RequestBody Todo todo, @PathVariable("categoryId") Long categoryId){
        todoService.addTodo(todo, categoryId);
    }

    @PutMapping("/{todoId}/update")
    public void updateTodo(@RequestBody Todo todo, @PathVariable("todoId") Long todoId){
        todoService.updateTodo(todo, todoId);
    }

    @DeleteMapping("/{todoId}/delete")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
    }
}
