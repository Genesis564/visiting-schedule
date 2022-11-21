package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import com.okei.visitingschedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScheduleServices {

    private ScheduleRepo scheduleRepo;
    @Autowired
    public ScheduleServices(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void addSchedule(User visitedUser, User visitorUser,String visitingWeek){
        Schedule schedule = new Schedule(Collections.singleton(Status.PLANNED),visitorUser,visitedUser,visitingWeek);
        scheduleRepo.save(schedule);
    }

    public void save(Schedule schedule){
        scheduleRepo.save(schedule);
    }

    public Schedule findFromDb(User visitedUser, User visitorUser,String visitingWeek){
       return scheduleRepo.findByVisitedUserAndVisitorUserAndVisitingWeek(visitedUser, visitorUser, visitingWeek);
    }

    public List<Schedule> findAll(){
        return (List<Schedule>) scheduleRepo.findAll();
    };

}
