package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.*;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.services.*;
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
    private final VisitingCriteriaService visitingCriteriaService;

    @Autowired
    public ScheduleController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, ScheduleServices scheduleServices, StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.scheduleServices = scheduleServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
    }


    @GetMapping("/add/visiting")
    public String addVisiting(Map<String, Object> model){
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();
        Iterable<VisitingCriteria> visitingCriteria = visitingCriteriaService.findAll();


        model.put("statuses", Status.values());
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
        model.put("criteries", visitingCriteria);
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

    @PostMapping("/add/create-visiting-criteria")
    public String  createCriteria(VisitingCriteriaRequestDTO visitingCriteriaRequestDTO, Map<String,Object> model){
        VisitingCriteria visitingCriteriaFromDb = visitingCriteriaService.findByCriteriaName(visitingCriteriaRequestDTO.getCritariaName());
        if (visitingCriteriaFromDb != null){
//            model.put("message","Discipline exists!");
            return "redirect:/admin/schedule/add/visiting";
        }
        visitingCriteriaService.addCriteria(visitingCriteriaRequestDTO.getCritariaName(),
                visitingCriteriaRequestDTO.getValueOfOnePoint(),
                visitingCriteriaRequestDTO.getValueOfTwoPoint(),
                visitingCriteriaRequestDTO.getValueOfThreePoint());
        return "redirect:/admin/schedule/add/visiting";
    }

}
