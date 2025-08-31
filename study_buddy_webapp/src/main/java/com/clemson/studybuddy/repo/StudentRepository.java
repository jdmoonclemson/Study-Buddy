package com.clemson.studybuddy.repo;


import com.clemson.studybuddy.domain.Student;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);


    // Students enrolled in a course code
    @Query("select s from Student s join s.courses c where upper(c) = upper(:code)")
    List<Student> findByCourse(@Param("code") String code);
}