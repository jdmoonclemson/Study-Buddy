package com.clemson.studybuddy.service;

import com.clemson.studybuddy.domain.Availability;
import com.clemson.studybuddy.domain.Student;
import com.clemson.studybuddy.dto.MatchResult;
import com.clemson.studybuddy.dto.OverlapSlot;
import com.clemson.studybuddy.repo.AvailabilityRepository;
import com.clemson.studybuddy.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

        // candidates are other students in the same course
        List<Student> courseMates = studentRepo.findByCourse(courseCode).stream()
                .filter(s -> !Objects.equals(s.getId(), studentId))
                .toList();
        if (courseMates.isEmpty()) return List.of();

        // group my availability by day-of-week
        Map<DayOfWeek, List<Availability>> myByDay = availabilityRepo.findByStudentId(studentId)
                .stream()
                .collect(Collectors.groupingBy(Availability::getDayOfWeek));

        List<MatchResult> results = new ArrayList<>();
        for (Student other : courseMates) {
            List<OverlapSlot> overlaps = new ArrayList<>();
            List<Availability> otherAvails = availabilityRepo.findByStudentId(other.getId());
            for (Availability b : otherAvails) {
                List<Availability> mySameDay = myByDay.getOrDefault(b.getDayOfWeek(), List.of());
                for (Availability a : mySameDay) {
                    OverlapSlot slot = overlap(a, b);
                    if (slot != null) overlaps.add(slot);
                }
            }
            results.add(new MatchResult(other, overlaps.size(), overlaps));
        }

        // NOTE: switched from MatchResult::overlapCount (record) to getOverlapCount (POJO)
        return results.stream()
                .sorted(Comparator.comparingInt(MatchResult::getOverlapCount).reversed())
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

    private LocalTime max(LocalTime x, LocalTime y) { return x.isAfter(y) ? x : y; }
    private LocalTime min(LocalTime x, LocalTime y) { return x.isBefore(y) ? x : y; }
}