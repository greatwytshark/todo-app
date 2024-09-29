package com.example.todo.services;

import com.example.todo.domain.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getTodos(Long categoryId);

    void addTodo(Todo todo, Long categoryId);

    void updateTodo(Todo todo, Long todoId);

    void deleteTodo(Long todoId);
}
