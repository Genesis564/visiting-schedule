package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.StudyGroup;
import com.okei.visitingschedule.repos.ScheduleRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupServices {
    @Autowired
    private final StudyGroupRepo studyGroupRepo;

    public StudyGroupServices(StudyGroupRepo studyGroupRepo) {
        this.studyGroupRepo = studyGroupRepo;
    }

    public void save(StudyGroup studyGroup){
        studyGroupRepo.save(studyGroup);
    }

}
