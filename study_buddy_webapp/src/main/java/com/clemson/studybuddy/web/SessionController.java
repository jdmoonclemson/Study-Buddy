package com.clemson.studybuddy.web;

import com.clemson.studybuddy.domain.SessionStatus;
import com.clemson.studybuddy.domain.Student;
import com.clemson.studybuddy.domain.StudySession;
import com.clemson.studybuddy.repo.StudentRepository;
import com.clemson.studybuddy.repo.StudySessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final StudySessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    public SessionController(StudySessionRepository s, StudentRepository st) {
        this.sessionRepo = s;
        this.studentRepo = st;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudySession create(@RequestParam("course") String course,
                               @RequestParam("requesterId") Long requesterId,
                               @RequestParam("inviteeId") Long inviteeId,
                               @RequestParam("start") String start,
                               @RequestParam("end") String end) {
        Student r = studentRepo.findById(requesterId).orElseThrow();
        Student i = studentRepo.findById(inviteeId).orElseThrow();
        StudySession ss = new StudySession(
                course,
                r,
                i,
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
        return sessionRepo.save(ss);
    }

    @PostMapping("/{id}/respond")
    public StudySession respond(@PathVariable("id") Long id,
                                @RequestParam("status") SessionStatus status) {
        StudySession ss = sessionRepo.findById(id).orElseThrow();
        ss.setStatus(status);
        return sessionRepo.save(ss);
    }

    @GetMapping
    public List<StudySession> list(@RequestParam("studentId") Long studentId) {
        return sessionRepo.findByRequesterIdOrInviteeId(studentId, studentId);
    }
}