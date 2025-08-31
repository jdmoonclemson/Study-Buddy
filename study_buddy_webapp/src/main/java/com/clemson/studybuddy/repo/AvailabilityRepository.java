package com.clemson.studybuddy.repo;


import com.clemson.studybuddy.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.*;
import java.util.*;


public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByStudentId(Long studentId);
    List<Availability> findByStudentIdIn(Collection<Long> studentIds);
    List<Availability> findByStudentIdAndDayOfWeek(Long studentId, DayOfWeek day);
}