package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Conclusion;
import com.okei.visitingschedule.entity.schedule.Event;
import com.okei.visitingschedule.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Event> findAll(Conclusion conclusion){
        return (List<Event>) eventRepo.findAllByConclusion(conclusion);
    }

    public Set<Event> eventsNameToEventsList(List<String> eventNames,Conclusion conclusion){
        Set<Event> events = new HashSet<>();
        for (String eventName : eventNames) {
            Event eventFromDB = eventRepo.findByName(eventName);
            if (eventFromDB !=null){
                events.add(eventFromDB);
            }else {
                Event event = new Event(eventName, conclusion);
                events.add(event);
                eventRepo.save(event);
            }
        }
        return events;
    }
}
