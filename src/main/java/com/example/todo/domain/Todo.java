package com.example.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todos")
public class Todo {
    @Id
    @Column(name = "todo_id")
    @GeneratedValue
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @ManyToMany
    private Set<Category> category;
}
