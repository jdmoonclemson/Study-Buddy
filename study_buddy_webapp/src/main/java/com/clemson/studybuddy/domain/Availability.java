package com.clemson.studybuddy.domain;


import jakarta.persistence.*;
import java.time.*;


@Entity
public class Availability {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    private Student student;


    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;


    private LocalTime startTime;
    private LocalTime endTime;


    public Availability() {}
    public Availability(Student s, DayOfWeek d, LocalTime start, LocalTime end) {
        this.student = s; this.dayOfWeek = d; this.startTime = start; this.endTime = end;
    }


    // getters/setters
    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}