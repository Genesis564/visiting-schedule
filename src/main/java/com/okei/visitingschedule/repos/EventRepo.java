package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.schedule.Conclusion;
import com.okei.visitingschedule.entity.schedule.Event;
import com.okei.visitingschedule.entity.schedule.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    public List<Event> findAllByConclusion(Conclusion conclusion);
    public Event findByName(String name);
}