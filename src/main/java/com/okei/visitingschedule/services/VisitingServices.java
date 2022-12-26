package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.repos.VisitingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class VisitingServices {

    private final VisitingRepo visitingRepo;

    @Autowired
    public VisitingServices(VisitingRepo visitingRepo) {
        this.visitingRepo = visitingRepo;
    }

    public void addVisiting(String purposeOfTheVisit, int numberOfStudents,
                            String lessonTopic, String purposeOfTheLesson,
                            String  date, StudyGroup studyGroup,
                            Position position, AcademicDiscipline academicDiscipline,
                            List<VisitingCriteria> criteria,
                            Schedule schedule) {
        visitingRepo.save(new Visiting(purposeOfTheVisit, numberOfStudents,
                lessonTopic, purposeOfTheLesson,
                date, studyGroup,
                position, academicDiscipline,
                schedule));
    }

    public Visiting findVisitingBySchedule(Schedule schedule){
        Visiting visiting = visitingRepo.findBySchedule(schedule);
        return visiting;
    }

    public Visiting findFromDb(Schedule schedule, String date) {
        return visitingRepo.findByScheduleAndDate(schedule, date);
    }

    public void save(Visiting visiting) {
        visitingRepo.save(visiting);
    }

    public List<Visiting> findAll() {
        return (List<Visiting>) visitingRepo.findAll();
    }
}
