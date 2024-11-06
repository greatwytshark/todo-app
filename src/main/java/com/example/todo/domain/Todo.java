package com.example.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private TodoCategory category;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

