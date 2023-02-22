package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.repos.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        return (List<Position>) positionRepo.findAll(Sort.by(Sort.Direction.ASC,"positionName"));
    }

    public void delete(Position position){
        positionRepo.delete(position);
    }
}
