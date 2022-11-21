package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    Schedule findByVisitedUserAndVisitorUserAndVisitingWeek(User visitedUser,User visitorUser,String visitingWeek);
}