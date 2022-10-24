package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.repos.AcademicDisciplineRepo;
import com.okei.visitingschedule.repos.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicDisciplineServices {
    @Autowired
    private final AcademicDisciplineRepo academicDisciplineRepo;

    public AcademicDisciplineServices(AcademicDisciplineRepo academicDisciplineRepo) {
        this.academicDisciplineRepo = academicDisciplineRepo;
    }

    public void save(AcademicDiscipline academicDiscipline){
        academicDisciplineRepo.save(academicDiscipline);
    }
}
