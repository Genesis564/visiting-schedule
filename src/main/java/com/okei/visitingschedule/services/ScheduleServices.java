package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.StudyGroup;
import com.okei.visitingschedule.repos.AcademicDisciplineRepo;
import com.okei.visitingschedule.repos.PositionRepo;
import com.okei.visitingschedule.repos.ScheduleRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServices {

    private final ScheduleRepo scheduleRepo;
    @Autowired
    public ScheduleServices(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void save(Schedule schedule){
        scheduleRepo.save(schedule);
    }

    public List<Schedule> findAll(){
        return (List<Schedule>) scheduleRepo.findAll();
    }
}
