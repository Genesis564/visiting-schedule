package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    Schedule findByVisitedUserAndVisitorUserAndVisitingWeek(User visitedUser,User visitorUser,String visitingWeek);
    List<Schedule> findAllByVisitedUser(User user);
    List<Schedule> findAllByVisitorUser(User user);

    List<Schedule> findAllByStatus(Status status);
}