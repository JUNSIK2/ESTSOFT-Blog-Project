package com.estsoft.springproject.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // @OneToMany 는 성능 상 좋지 않음
    @OneToMany(mappedBy = "team")
    private List<Members> members = new ArrayList<>();
}