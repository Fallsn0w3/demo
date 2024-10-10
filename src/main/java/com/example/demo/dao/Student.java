package com.example.demo.dao;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="age")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
