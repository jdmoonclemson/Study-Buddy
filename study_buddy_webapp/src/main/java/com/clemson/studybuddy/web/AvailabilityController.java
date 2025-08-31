package com.clemson.studybuddy.web;


import com.clemson.studybuddy.domain.*;
import com.clemson.studybuddy.repo.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.time.*;
import java.util.*;


@RestController
@RequestMapping("/api")
public class AvailabilityController {
    private final AvailabilityRepository availabilityRepo;
    private final StudentRepository studentRepo;


    public AvailabilityController(AvailabilityRepository a, StudentRepository s){
        this.availabilityRepo = a; this.studentRepo = s;
    }


    @GetMapping("/students/{id}/availability")
    public List<Availability> list(@PathVariable Long id){
        return availabilityRepo.findByStudentId(id);
    }


    @PostMapping("/students/{id}/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public Availability add(@PathVariable Long id,
                            @RequestParam String day,
                            @RequestParam String start,
                            @RequestParam String end){
        Student s = studentRepo.findById(id).orElseThrow();
        DayOfWeek dow = DayOfWeek.valueOf(day.toUpperCase());
        Availability a = new Availability(s, dow, LocalTime.parse(start), LocalTime.parse(end));
        return availabilityRepo.save(a);
    }


    @DeleteMapping("/availability/{availId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long availId){
        availabilityRepo.deleteById(availId);
    }
}