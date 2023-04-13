package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Visiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitingRepo extends JpaRepository<Visiting, Long> {
    Visiting findByScheduleAndDate(Schedule schedule,String date);
    Visiting findBySchedule(Schedule schedule);
}