package com.estsoft.springproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}
