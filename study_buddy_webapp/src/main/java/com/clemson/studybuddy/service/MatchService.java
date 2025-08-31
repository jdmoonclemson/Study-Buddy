package com.clemson.studybuddy.service;


import com.clemson.studybuddy.domain.*;
import com.clemson.studybuddy.dto.*;
import com.clemson.studybuddy.repo.*;
import org.springframework.stereotype.Service;


import java.time.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MatchService {
    private final AvailabilityRepository availabilityRepo;
    private final StudentRepository studentRepo;


    public MatchService(AvailabilityRepository a, StudentRepository s) {
        this.availabilityRepo = a;
        this.studentRepo = s;
    }


    public List<MatchResult> suggestMatches(Long studentId, String courseCode) {
        Student me = studentRepo.findById(studentId).orElseThrow();
// candidates are other students in same course
        List<Student> courseMates = studentRepo.findByCourse(courseCode).stream()
                .filter(s -> !Objects.equals(s.getId(), studentId))
                .toList();
        if (courseMates.isEmpty()) return List.of();


        Map<Long, List<Availability>> myByDay = availabilityRepo.findByStudentId(studentId)
                .stream().collect(Collectors.groupingBy(a -> a.getDayOfWeek().getValue() * 1L));


        List<MatchResult> results = new ArrayList<>();
        for (Student other : courseMates) {
            List<OverlapSlot> overlaps = new ArrayList<>();
            for (Availability b : availabilityRepo.findByStudentId(other.getId())) {
                List<Availability> mySameDay = myByDay.getOrDefault((long)b.getDayOfWeek().getValue(), List.of());
                for (Availability a : mySameDay) {
                    OverlapSlot slot = overlap(a, b);
                    if (slot != null) overlaps.add(slot);
                }
            }
            results.add(new MatchResult(other, overlaps.size(), overlaps));
        }
        return results.stream()
                .sorted(Comparator.comparingInt(MatchResult::overlapCount).reversed())
                .limit(3)
                .toList();
    }


    private OverlapSlot overlap(Availability a, Availability b) {
        if (a.getDayOfWeek() != b.getDayOfWeek()) return null;
        LocalTime start = max(a.getStartTime(), b.getStartTime());
        LocalTime end = min(a.getEndTime(), b.getEndTime());
        if (start.isBefore(end)) return new OverlapSlot(a.getDayOfWeek(), start, end);
        return null;
    }
    private LocalTime max(LocalTime x, LocalTime y){ return x.isAfter(y)? x : y; }
    private LocalTime min(LocalTime x, LocalTime y){ return x.isBefore(y)? x : y; }
}