package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.repos.AcademicDisciplineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicDisciplineServices {

    private final AcademicDisciplineRepo academicDisciplineRepo;
    @Autowired
    public AcademicDisciplineServices(AcademicDisciplineRepo academicDisciplineRepo) {
        this.academicDisciplineRepo = academicDisciplineRepo;
    }

    public AcademicDiscipline findByDisciplineName(String disciplineName){
        return academicDisciplineRepo.findByDisciplineName(disciplineName);
    }

    public void addDiscipline(String disciplineName){
        academicDisciplineRepo.save(new AcademicDiscipline(disciplineName));
    }

    public void save(AcademicDiscipline academicDiscipline){
        academicDisciplineRepo.save(academicDiscipline);
    }

    public List<AcademicDiscipline> findAll(){
        return (List<AcademicDiscipline>) academicDisciplineRepo.findAll(Sort.by(Sort.Direction.ASC,"disciplineName"));
    }

    public void delete(AcademicDiscipline academicDiscipline){
        academicDisciplineRepo.delete(academicDiscipline);
    }
}
