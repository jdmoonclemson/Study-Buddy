package com.clemson.studybuddy.repo;


import com.clemson.studybuddy.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByRequesterIdOrInviteeId(Long requesterId, Long inviteeId);
}