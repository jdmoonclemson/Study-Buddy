package com.clemson.studybuddy;


import com.clemson.studybuddy.domain.*;
import com.clemson.studybuddy.dto.MatchResult;
import com.clemson.studybuddy.repo.*;
import com.clemson.studybuddy.service.MatchService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.time.*;
import java.util.*;


@DataJpaTest
@Import(MatchService.class)
class MatchServiceTests {
    @Autowired StudentRepository students;
    @Autowired AvailabilityRepository avails;
    @Autowired MatchService matchService;


    @Test
    void overlapSuggestsTopMatch() {
        Student a = students.save(new Student("A", "a@x.com"));
        a.getCourses().add("CPSC-2150");
        students.save(a);
        Student b = students.save(new Student("B", "b@x.com"));
        b.getCourses().add("CPSC-2150");
        students.save(b);
        Student c = students.save(new Student("C", "c@x.com"));
        c.getCourses().add("CPSC-2150");
        students.save(c);


        avails.save(new Availability(a, DayOfWeek.MONDAY, LocalTime.of(14,0), LocalTime.of(16,0)));
        avails.save(new Availability(b, DayOfWeek.MONDAY, LocalTime.of(15,0), LocalTime.of(17,0))); // 1 hour overlap
        avails.save(new Availability(c, DayOfWeek.MONDAY, LocalTime.of(18,0), LocalTime.of(19,0))); // no overlap


        List<MatchResult> results = matchService.suggestMatches(a.getId(), "CPSC-2150");
        Assertions.assertFalse(results.isEmpty());
        Assertions.assertEquals("B", results.get(0).student().getName());
        Assertions.assertTrue(results.get(0).overlapCount() >= 1);
    }
}