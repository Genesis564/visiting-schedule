package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.Visiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Visiting, Long> {
    Visiting findByDateAndStatusAndStudyGroupAndPositionAndAcademicDiscipline(String date, String status, String studyGroup, String position, String academicDiscipline );
}