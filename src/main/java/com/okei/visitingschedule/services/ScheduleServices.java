package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.repos.ScheduleRepo;
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

    public void save(Visiting visiting){
        scheduleRepo.save(visiting);
    }

    public List<Visiting> findAll(){
        return (List<Visiting>) scheduleRepo.findAll();
    }
}
