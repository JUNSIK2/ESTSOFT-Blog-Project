package com.estsoft.springproject.domain.entity;

import jakarta.persistence.*;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Members member;
}