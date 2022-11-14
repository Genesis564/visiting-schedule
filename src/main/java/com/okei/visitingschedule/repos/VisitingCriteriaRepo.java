package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.entity.schedule.VisitingCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitingCriteriaRepo extends JpaRepository<VisitingCriteria, Long> {
    VisitingCriteria findByCriteriaName(String criteriaName);
}