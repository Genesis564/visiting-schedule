package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class VisitingServices {

    private final ScheduleRepo scheduleRepo;
    @Autowired
    public VisitingServices(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void addVisiting(Date date, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline,
                            List<VisitingCriteria> criteria,List<CriteriaScore> criteriaScore){
        scheduleRepo.save(new Visiting(date, Collections.singleton(Status.PLANNED),studyGroup,position,academicDiscipline,criteria,criteriaScore));
    }

    public List<CriteriaScore> fillCriteriaScore(List<VisitingCriteria> visitingCriteria){
        List<CriteriaScore> criteriaScores = new ArrayList<>();
        for (VisitingCriteria criteria: visitingCriteria) {
            criteriaScores.add(new CriteriaScore(criteria.getScore()));
        }
        return criteriaScores;
    }
    public void save(Visiting visiting){
        scheduleRepo.save(visiting);
    }

    public List<Visiting> findAll(){
        return (List<Visiting>) scheduleRepo.findAll();
    }
}
