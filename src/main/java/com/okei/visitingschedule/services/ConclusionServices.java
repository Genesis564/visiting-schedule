package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Conclusion;
import com.okei.visitingschedule.entity.schedule.Event;
import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.repos.ConclusionRepo;
import com.okei.visitingschedule.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConclusionServices {

    private final ConclusionRepo conclusionRepo;
    @Autowired
    public ConclusionServices(ConclusionRepo conclusionRepo) {
        this.conclusionRepo = conclusionRepo;
    }

    public void save(Conclusion conclusion){
        conclusionRepo.save(conclusion);
    }


    public void addConclusion(String virtuesOfOccupation, String problems, Visiting visiting){
        conclusionRepo.save(new Conclusion(virtuesOfOccupation,problems,visiting));
    }

    public List<Conclusion> findAll(){
        return (List<Conclusion>) conclusionRepo.findAll();
    }
    public Conclusion findConclusionByVisiting(Visiting visiting){
        return conclusionRepo.findByVisiting(visiting);
    }
}
