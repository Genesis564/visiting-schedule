package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepo extends JpaRepository<StudyGroup, Long> {
    StudyGroup findByGroupName(String groupName);
}