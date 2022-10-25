package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.repos.PositionRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServices {

    private final PositionRepo positionRepo;
    @Autowired
    public PositionServices(PositionRepo positionRepo) {
        this.positionRepo = positionRepo;
    }

    public void save(Position position){
        positionRepo.save(position);
    }

    public Position findByPositionName(String positionName){
        return positionRepo.findByPositionName(positionName);
    }

    public void addPosition(String positionName){
        positionRepo.save(new Position(positionName));
    }

    public List<Position> findAll(){
        return (List<Position>) positionRepo.findAll();
    }
}
