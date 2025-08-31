package com.clemson.studybuddy.domain;


import jakarta.persistence.*;
import java.util.*;


@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;


    // Store course codes like CPSC-2150, MATH-2060
    @ElementCollection
    @CollectionTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "course_code")
    private Set<String> courses = new HashSet<>();


    public Student() {}
    public Student(String name, String email) { this.name = name; this.email = email; }


    // getters/setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<String> getCourses() { return courses; }
    public void setCourses(Set<String> courses) { this.courses = courses; }
}