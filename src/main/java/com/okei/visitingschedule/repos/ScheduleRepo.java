package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    Schedule findByVisitedUserAndVisitorUserAndVisitingWeek(User visitedUser, User visitorUser, String visitingWeek);

    @Query(value = "select * from schedule s join schedule_status ss on s.id = ss.schedule_id where status = :status order by visiting_week",
            nativeQuery = true)
    List<Schedule> findAllByStatus(@Param("status") String status);

    @Query(value = "select * from schedule s join schedule_status ss on s.id = ss.schedule_id join usr u on u.id = s.visited_user_id join usr u2 on u2.id = s.visiter_user_id where status = :status and (visited_user_id = :userId or visiter_user_id = :userId) order by visiting_week",
            nativeQuery = true)
    List<Schedule> findAllByStatusAndUser(@Param("status") String status, @Param("userId") Long userId);

    List<Schedule> findAllByVisitorUserOrVisitedUserOrderByStatusDesc(User user, User user2);

    List<Schedule> findAll(Sort sort);

}