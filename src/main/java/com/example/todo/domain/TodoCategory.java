package com.example.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class TodoCategory {
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
