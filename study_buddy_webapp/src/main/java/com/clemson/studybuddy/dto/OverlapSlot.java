package com.clemson.studybuddy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OverlapSlot {
    private DayOfWeek day;

    // ISO times like "14:00:00" (Spring already configures JavaTimeModule)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime start;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime end;

    public OverlapSlot() {
    }

    public OverlapSlot(DayOfWeek day, LocalTime start, LocalTime end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}