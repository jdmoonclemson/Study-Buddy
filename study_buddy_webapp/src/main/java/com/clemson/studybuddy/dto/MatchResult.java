package com.clemson.studybuddy.dto;


import com.clemson.studybuddy.domain.Student;
import java.util.*;


public final class MatchResult {
    private final Student student;
    private final int overlapCount;
    private final List<OverlapSlot> overlaps;

    public MatchResult(Student student, int overlapCount, List<OverlapSlot> overlaps) {
        this.student = student;
        this.overlapCount = overlapCount;
        this.overlaps = overlaps;
    }

    public Student student() {
        return student;
    }

    public int overlapCount() {
        return overlapCount;
    }

    public List<OverlapSlot> overlaps() {
        return overlaps;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        MatchResult that = (MatchResult) obj;
        return Objects.equals(this.student, that.student) &&
                this.overlapCount == that.overlapCount &&
                Objects.equals(this.overlaps, that.overlaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, overlapCount, overlaps);
    }

    @Override
    public String toString() {
        return "MatchResult[" +
                "student=" + student + ", " +
                "overlapCount=" + overlapCount + ", " +
                "overlaps=" + overlaps + ']';
    }
}