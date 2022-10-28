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

import java.util.Collections;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
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


    @GetMapping("/admin/schedule/add")
    public String addSchedule(Map<String, Object> model){
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

    @PostMapping("/admin/schedule/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createSchedule(){
        return "redirect:/admin";
    }


    @PostMapping("/admin/schedule/add/create-study-group")
    public String createStudyGroup(StudyGroupRequestDto studyGroupRequestDto, Map<String,Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null){
//            model.put("message","Study group exists!");
            return "redirect:/admin/schedule/add";
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
        return "redirect:/admin/schedule/add";
    }


    @PostMapping("/admin/schedule/add/create-position")
    public String createPosition(PositionRequestDto positionRequestDto, Map<String,Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        if (positionFromDb != null){
//            model.put("message","Position exists!");
            return "redirect:/admin/schedule/add";
        }
        positionServices.addPosition(positionRequestDto.getPositionName());
        return "redirect:/admin/schedule/add";
    }


    @PostMapping("/admin/schedule/add/create-discipline")
    public String createDiscipline(AcademicDisciplineRequestDto disciplineRequestDto, Map<String,Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null){
//            model.put("message","Discipline exists!");
            return "redirect:/admin/schedule/add";
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
        return "redirect:/admin/schedule/add";
    }

}
