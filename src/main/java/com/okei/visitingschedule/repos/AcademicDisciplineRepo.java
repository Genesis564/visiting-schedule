package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicDisciplineRepo extends JpaRepository<AcademicDiscipline, Long> {
}