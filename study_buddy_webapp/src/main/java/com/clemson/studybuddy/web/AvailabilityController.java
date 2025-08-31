package com.clemson.studybuddy.web;

import com.clemson.studybuddy.domain.Availability;
import com.clemson.studybuddy.domain.Student;
import com.clemson.studybuddy.repo.AvailabilityRepository;
import com.clemson.studybuddy.repo.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AvailabilityController {
    private final AvailabilityRepository availabilityRepo;
    private final StudentRepository studentRepo;

    public AvailabilityController(AvailabilityRepository a, StudentRepository s) {
        this.availabilityRepo = a;
        this.studentRepo = s;
    }

    @GetMapping("/students/{id}/availability")
    public List<Availability> list(@PathVariable("id") Long id) {
        return availabilityRepo.findByStudentId(id);
    }

    @PostMapping("/students/{id}/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public Availability add(@PathVariable("id") Long id,
                            @RequestParam("day") String day,
                            @RequestParam("start") String start,
                            @RequestParam("end") String end) {
        Student s = studentRepo.findById(id).orElseThrow();
        DayOfWeek dow = DayOfWeek.valueOf(day.toUpperCase());
        Availability a = new Availability(s, dow, LocalTime.parse(start), LocalTime.parse(end));
        return availabilityRepo.save(a);
    }

    @DeleteMapping("/availability/{availId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("availId") Long availId) {
        availabilityRepo.deleteById(availId);
    }
}