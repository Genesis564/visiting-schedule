package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.repos.PositionRepo;
import com.okei.visitingschedule.repos.StudyGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServices {
    @Autowired
    private final PositionRepo positionRepo;

    public PositionServices(PositionRepo positionRepo) {
        this.positionRepo = positionRepo;
    }

    public void save(Position position){
        positionRepo.save(position);
    }
}
