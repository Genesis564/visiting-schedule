package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.AcademicDisciplineRequestDto;
import com.okei.visitingschedule.dto.PositionRequestDto;
import com.okei.visitingschedule.dto.StudyGroupRequestDto;
import com.okei.visitingschedule.dto.VisitingCriteriaRequestDTO;
import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.entity.schedule.StudyGroup;
import com.okei.visitingschedule.entity.schedule.VisitingCriteria;
import com.okei.visitingschedule.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/schedule/add/")
public class VisitingComponentsController {
    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final StudyGroupServices studyGroupServices;
    private final VisitingCriteriaService visitingCriteriaService;

    @Autowired
    public VisitingComponentsController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices,  StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
    }

    @PostMapping("create-study-group")
    public String  createStudyGroup(@RequestParam("scheduleId") long scheduleId,StudyGroupRequestDto studyGroupRequestDto, Map<String,Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null){
//            model.put("message","Study group exists!");
            return "redirect:/schedule/add/" + scheduleId;
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
        return "redirect:/schedule/add/" + scheduleId;
    }


    @PostMapping("create-position")
    public String  createPosition(@RequestParam("scheduleId") long scheduleId,PositionRequestDto positionRequestDto, Map<String,Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        //            model.put("message","Position exists!");
        if (positionFromDb != null) {
            return "redirect:/schedule/add/" + scheduleId;
        }
        positionServices.addPosition(positionRequestDto.getPositionName());
        return "redirect:/schedule/add/" + scheduleId;
    }


    @PostMapping("create-discipline")
    public String  createDiscipline(@RequestParam("scheduleId") long scheduleId,AcademicDisciplineRequestDto disciplineRequestDto, Map<String,Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null){
//            model.put("message","Discipline exists!");
            return "redirect:/schedule/add/" + scheduleId;
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
        return "redirect:/schedule/add/" + scheduleId;
    }

    @PostMapping("create-visiting-criteria")
    public String  createCriteria(@RequestParam("scheduleId") long scheduleId,VisitingCriteriaRequestDTO visitingCriteriaRequestDTO, Map<String,Object> model){
        VisitingCriteria visitingCriteriaFromDb = visitingCriteriaService.findByCriteriaName(visitingCriteriaRequestDTO.getCritariaName());
        if (visitingCriteriaFromDb != null){
//            model.put("me ssage","Discipline exists!");
            return "redirect:/schedule/add/" + scheduleId;
        }
        visitingCriteriaService.addCriteria(visitingCriteriaRequestDTO.getCritariaName(),
                visitingCriteriaRequestDTO.getValueOfOnePoint(),
                visitingCriteriaRequestDTO.getValueOfTwoPoint(),
                visitingCriteriaRequestDTO.getValueOfThreePoint());
        return "redirect:/schedule/add/" + scheduleId;
    }

}
