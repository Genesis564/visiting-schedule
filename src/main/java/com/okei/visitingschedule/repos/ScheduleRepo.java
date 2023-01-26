package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    Schedule findByVisitedUserAndVisitorUserAndVisitingWeek(User visitedUser,User visitorUser,String visitingWeek);

    List<Schedule> findAllByStatus(Status status);

    List<Schedule> findAllByVisitorUserOrVisitedUserOrderByStatusDesc(User user,User user2);
}