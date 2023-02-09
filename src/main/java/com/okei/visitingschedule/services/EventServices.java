package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Conclusion;
import com.okei.visitingschedule.entity.schedule.Event;
import com.okei.visitingschedule.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServices {

    private final EventRepo eventRepo;
    @Autowired
    public EventServices(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public void save(Event event){
        eventRepo.save(event);
    }


    public void addEvent(String eventName, Conclusion conclusion){
        eventRepo.save(new Event(eventName,conclusion));
    }

    public List<Event> findAll(){
        return (List<Event>) eventRepo.findAll();
    }
}
