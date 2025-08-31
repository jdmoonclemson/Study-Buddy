package com.clemson.studybuddy.dto;


import java.time.*;
import java.util.Objects;


public final class OverlapSlot {
    private final DayOfWeek day;
    private final LocalTime start;
    private final LocalTime end;

    public OverlapSlot(DayOfWeek day, LocalTime start, LocalTime end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public DayOfWeek day() {
        return day;
    }

    public LocalTime start() {
        return start;
    }

    public LocalTime end() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        OverlapSlot that = (OverlapSlot) obj;
        return Objects.equals(this.day, that.day) &&
                Objects.equals(this.start, that.start) &&
                Objects.equals(this.end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, start, end);
    }

    @Override
    public String toString() {
        return "OverlapSlot[" +
                "day=" + day + ", " +
                "start=" + start + ", " +
                "end=" + end + ']';
    }
}