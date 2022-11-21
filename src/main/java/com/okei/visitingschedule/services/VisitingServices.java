package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.repos.VisitingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VisitingServices {

    private final VisitingRepo visitingRepo;
    @Autowired
    public VisitingServices(VisitingRepo visitingRepo) {
        this.visitingRepo = visitingRepo;
    }

    public void addVisiting(Date date, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline,
                            List<VisitingCriteria> criteria,List<CriteriaScore> criteriaScore){
//        visitingRepo.save(new Visiting());
    }

    public List<CriteriaScore> fillCriteriaScore(List<VisitingCriteria> visitingCriteria){
        List<CriteriaScore> criteriaScores = new ArrayList<>();
        for (VisitingCriteria criteria: visitingCriteria) {
            criteriaScores.add(new CriteriaScore(criteria.getScore()));
        }
        return criteriaScores;
    }
    public void save(Visiting visiting){
        visitingRepo.save(visiting);
    }

    public List<Visiting> findAll(){
        return (List<Visiting>) visitingRepo.findAll();
    }
}
