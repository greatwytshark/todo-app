package com.example.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany
    private Set<User> users;
}
