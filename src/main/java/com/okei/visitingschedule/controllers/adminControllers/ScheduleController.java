package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.AcademicDisciplineRequestDto;
import com.okei.visitingschedule.dto.PositionRequestDto;
import com.okei.visitingschedule.dto.StudyGroupRequestDto;
import com.okei.visitingschedule.dto.UserRequestDTO;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.services.AcademicDisciplineServices;
import com.okei.visitingschedule.services.PositionServices;
import com.okei.visitingschedule.services.ScheduleServices;
import com.okei.visitingschedule.services.StudyGroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/schedule")
public class ScheduleController {
    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final ScheduleServices scheduleServices;
    private final StudyGroupServices studyGroupServices;

    @Autowired
    public ScheduleController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, ScheduleServices scheduleServices, StudyGroupServices studyGroupServices) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.scheduleServices = scheduleServices;
        this.studyGroupServices = studyGroupServices;
    }


    @GetMapping("/add/visiting")
    public String addVisiting(Map<String, Object> model){
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();


        model.put("statuses", Status.values());
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
        model.put("criteries", VisitingCriteria.values());
        return "createSchedule";
    }

    @PostMapping("/add/visiting")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createSchedule(){
        return "redirect:/admin";
    }


    @PostMapping("/add/create-study-group")
    public void createStudyGroup(StudyGroupRequestDto studyGroupRequestDto, Map<String,Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null){
//            model.put("message","Study group exists!");
            return;
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
    }


    @PostMapping("/add/create-position")
    public void createPosition(PositionRequestDto positionRequestDto, Map<String,Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        //            model.put("message","Position exists!");
        if (positionFromDb != null) {
            return;
        }
        positionServices.addPosition(positionRequestDto.getPositionName());

    }


    @PostMapping("/add/create-discipline")
    public void createDiscipline(AcademicDisciplineRequestDto disciplineRequestDto, Map<String,Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null){
//            model.put("message","Discipline exists!");
            return;
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
    }

}
