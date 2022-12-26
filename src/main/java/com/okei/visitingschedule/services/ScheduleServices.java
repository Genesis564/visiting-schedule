package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import com.okei.visitingschedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
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

    public List<Schedule> findByUser(User user){
        List<Schedule> schedules = new ArrayList<>();
        if (user.getRoles().contains(Role.USER_VISITOR)){
            schedules.addAll(scheduleRepo.findAllByVisitorUser(user));
        }
        if (user.getRoles().contains(Role.USER_VISITED)){
            schedules.addAll(scheduleRepo.findAllByVisitedUser(user));
        }
        return schedules;
    }
    public List<Schedule> findAll(){
        return (List<Schedule>) scheduleRepo.findAll();
    }

    @Scheduled(cron = "@weekly")
    public void updateScheduleStatus(){
        Calendar date= new GregorianCalendar();
        int week = date.get(Calendar.WEEK_OF_YEAR);
        int year = date.get(Calendar.YEAR);
        List<Schedule> scheduleList = scheduleRepo.findAllByStatus(Status.PLANNED);
        Set<Status> statusSet = new HashSet<>();
        statusSet.add(Status.OVERDUE);
        for (Schedule schedule:scheduleList) {
            int scheduleWeek = Integer.parseInt(schedule.getVisitingWeek().substring(6));
            int scheduleYear = Integer.parseInt(schedule.getVisitingWeek().substring(0,4));
            if (scheduleWeek < week && scheduleYear<=year){
                schedule.setStatus(statusSet);
                scheduleRepo.save(schedule);
            }
        }
    }

}
