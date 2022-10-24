package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.StudyGroup;
import com.okei.visitingschedule.repos.ScheduleRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupServices {

    private final StudyGroupRepo studyGroupRepo;
    @Autowired
    public StudyGroupServices(StudyGroupRepo studyGroupRepo) {
        this.studyGroupRepo = studyGroupRepo;
    }

    public void save(StudyGroup studyGroup){
        studyGroupRepo.save(studyGroup);
    }

    public List<StudyGroup> findAll(){
        return (List<StudyGroup>) studyGroupRepo.findAll();
    }

}
