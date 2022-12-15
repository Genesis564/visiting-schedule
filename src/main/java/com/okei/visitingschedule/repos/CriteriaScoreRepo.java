package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.CriteriaScore;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.entity.schedule.VisitingCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaScoreRepo extends JpaRepository<CriteriaScore, Long> {
}