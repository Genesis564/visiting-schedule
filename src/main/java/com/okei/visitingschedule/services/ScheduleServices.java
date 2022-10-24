package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.repos.AcademicDisciplineRepo;
import com.okei.visitingschedule.repos.PositionRepo;
import com.okei.visitingschedule.repos.ScheduleRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServices {
    @Autowired
    private final ScheduleRepo scheduleRepo;

    public ScheduleServices(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void save(Schedule schedule){
        scheduleRepo.save(schedule);
    }
}
