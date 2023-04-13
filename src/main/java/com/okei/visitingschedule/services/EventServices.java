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


    public Event addEvent(String eventName, Conclusion conclusion){
        Event event = new Event(eventName,conclusion);
        eventRepo.save(event);
        return event;
    }

    public List<Event> findAll(){
        return (List<Event>) eventRepo.findAll();
    }
    public List<Event> findAll(Conclusion conclusion){
        return (List<Event>) eventRepo.findAllByConclusion(conclusion);
    }


    public Event editEvent(Long id,String eventName){
        Event event = eventRepo.findById(id).get();
        event.setEventName(eventName);
        return event;
    }

    public void delete(Event event){
        eventRepo.delete(event);
    }
}
