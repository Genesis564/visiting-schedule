package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.CriteriaScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaScoreRepo extends JpaRepository<CriteriaScore, Long> {
}