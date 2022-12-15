package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.entity.schedule.CriteriaScore;
import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.entity.schedule.VisitingCriteria;
import com.okei.visitingschedule.repos.AcademicDisciplineRepo;
import com.okei.visitingschedule.repos.VisitingCriteriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VisitingCriteriaService {

    private final VisitingCriteriaRepo visitingCriteriaRepo;
    @Autowired
    public VisitingCriteriaService(VisitingCriteriaRepo visitingCriteriaRepo) {
        this.visitingCriteriaRepo = visitingCriteriaRepo;
    }

    public void addCriteria(String criteriaName,String valueOfOnePoint,String valueOfTwoPoint,String valueOfThreePoint){
        visitingCriteriaRepo.save(new VisitingCriteria(criteriaName,valueOfOnePoint,valueOfTwoPoint,valueOfThreePoint));
    }


    public void save(VisitingCriteria visitingCriteria){
        visitingCriteriaRepo.save(visitingCriteria);
    }
    public VisitingCriteria findByCriteriaName(String criteriaName){
        return visitingCriteriaRepo.findByCriteriaName(criteriaName);
    }

    public VisitingCriteria findById(Long id){
        return visitingCriteriaRepo.findById(id).get();
    }

    public List<VisitingCriteria> findAll(){
        return (List<VisitingCriteria>) visitingCriteriaRepo.findAll();
    }
}
