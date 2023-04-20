package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.CriteriaType;
import com.okei.visitingschedule.entity.schedule.VisitingCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface VisitingCriteriaRepo extends JpaRepository<VisitingCriteria, Long> {
    VisitingCriteria findByCriteriaName(String criteriaName);
    List<VisitingCriteria> findAllByCriteriaTypes(CriteriaType criteriaTypes);
}