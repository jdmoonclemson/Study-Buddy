package com.clemson.studybuddy.web;


import com.clemson.studybuddy.domain.*;
import com.clemson.studybuddy.repo.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.time.*;
import java.util.*;


@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final StudySessionRepository sessionRepo;
    private final StudentRepository studentRepo;


    public SessionController(StudySessionRepository s, StudentRepository st){
        this.sessionRepo = s; this.studentRepo = st;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudySession create(@RequestParam String course,
                               @RequestParam Long requesterId,
                               @RequestParam Long inviteeId,
                               @RequestParam String start,
                               @RequestParam String end){
        Student r = studentRepo.findById(requesterId).orElseThrow();
        Student i = studentRepo.findById(inviteeId).orElseThrow();
        StudySession ss = new StudySession(course, r, i, LocalDateTime.parse(start), LocalDateTime.parse(end));
        return sessionRepo.save(ss);
    }


    @PostMapping("/{id}/respond")
    public StudySession respond(@PathVariable Long id, @RequestParam SessionStatus status){
        StudySession ss = sessionRepo.findById(id).orElseThrow();
        ss.setStatus(status);
        return sessionRepo.save(ss);
    }


    @GetMapping
    public List<StudySession> list(@RequestParam Long studentId){
        return sessionRepo.findByRequesterIdOrInviteeId(studentId, studentId);
    }
}