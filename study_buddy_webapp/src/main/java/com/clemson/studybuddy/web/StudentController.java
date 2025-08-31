package com.clemson.studybuddy.web;


import com.clemson.studybuddy.domain.Student;
import com.clemson.studybuddy.repo.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;
    public StudentController(StudentRepository repo){ this.repo = repo; }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student s){
        return repo.save(s);
    }


    @GetMapping("/{id}")
    public Student get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }


    @GetMapping
    public List<Student> list(@RequestParam(required=false) String course){
        if (course == null || course.isBlank()) return repo.findAll();
        return repo.findByCourse(course);
    }


    @PostMapping("/{id}/courses")
    public Student addCourse(@PathVariable Long id, @RequestParam String course){
        Student s = repo.findById(id).orElseThrow();
        s.getCourses().add(course.trim());
        return repo.save(s);
    }


    @DeleteMapping("/{id}/courses")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCourse(@PathVariable Long id, @RequestParam String course){
        Student s = repo.findById(id).orElseThrow();
        s.getCourses().removeIf(c -> c.equalsIgnoreCase(course));
        repo.save(s);
    }
}