package com.clemson.studybuddy.dto;

import com.clemson.studybuddy.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class MatchResult {
    private Student student;
    private int overlapCount;
    private List<OverlapSlot> overlaps = new ArrayList<>();

    public MatchResult() {
    }

    public MatchResult(Student student, int overlapCount, List<OverlapSlot> overlaps) {
        this.student = student;
        this.overlapCount = overlapCount;
        if (overlaps != null) {
            this.overlaps = overlaps;
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getOverlapCount() {
        return overlapCount;
    }

    public void setOverlapCount(int overlapCount) {
        this.overlapCount = overlapCount;
    }

    public List<OverlapSlot> getOverlaps() {
        return overlaps;
    }

    public void setOverlaps(List<OverlapSlot> overlaps) {
        this.overlaps = overlaps;
    }
}