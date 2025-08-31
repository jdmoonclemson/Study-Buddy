package com.clemson.studybuddy.domain;


import jakarta.persistence.*;
import java.time.*;


@Entity
public class StudySession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String courseCode;


    @ManyToOne(optional = false)
    private Student requester;


    @ManyToOne(optional = false)
    private Student invitee;


    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    @Enumerated(EnumType.STRING)
    private SessionStatus status = SessionStatus.REQUESTED;


    public StudySession() {}
    public StudySession(String course, Student requester, Student invitee, LocalDateTime start, LocalDateTime end) {
        this.courseCode = course; this.requester = requester; this.invitee = invitee; this.startDateTime = start; this.endDateTime = end;
    }


    // getters/setters
    public Long getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public Student getRequester() { return requester; }
    public void setRequester(Student requester) { this.requester = requester; }
    public Student getInvitee() { return invitee; }
    public void setInvitee(Student invitee) { this.invitee = invitee; }
    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }
    public SessionStatus getStatus() { return status; }
    public void setStatus(SessionStatus status) { this.status = status; }
}