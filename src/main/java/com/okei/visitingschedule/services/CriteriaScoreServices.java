package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.CriteriaScore;
import com.okei.visitingschedule.repos.CriteriaScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CriteriaScoreServices {

    private final CriteriaScoreRepo criteriaScoreRepo;

    @Autowired
    public CriteriaScoreServices(CriteriaScoreRepo criteriaScoreRepo) {
        this.criteriaScoreRepo = criteriaScoreRepo;
    }

    public void save(CriteriaScore CriteriaScore){
        criteriaScoreRepo.save(CriteriaScore);
    }

    public List<CriteriaScore> sortCriteria(Set<CriteriaScore> criteriaScore){
        List<CriteriaScore> sortedCriteriaScore = new ArrayList<>(criteriaScore);
        Collections.sort(sortedCriteriaScore,new Comparator<CriteriaScore>()
        {
            @Override
            public int compare (CriteriaScore criteriaScore1, CriteriaScore criteriaScore2){
                return (int) (criteriaScore1.getVisitingCriteria().getId() - criteriaScore2.getVisitingCriteria().getId());
            }
        });
        return sortedCriteriaScore;
    }

}
