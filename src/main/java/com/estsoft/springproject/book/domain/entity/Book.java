package com.estsoft.springproject.book.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(name = "author", nullable = false)
    private String author;
}
