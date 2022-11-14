package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.*;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.repos.CriteriaScoreRepo;
import com.okei.visitingschedule.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/schedule")
public class ScheduleController {
    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final VisitingServices visitingServices;
    private final CriteriaScoreRepo criteriaScoreRepo;
    private final StudyGroupServices studyGroupServices;
    private final VisitingCriteriaService visitingCriteriaService;

    @Autowired
    public ScheduleController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, VisitingServices visitingServices, StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService, CriteriaScoreRepo criteriaScoreRepo) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.visitingServices = visitingServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
        this.criteriaScoreRepo = criteriaScoreRepo;
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
    public String createSchedule(VisitingRequestDTO visitingRequestDTO, Map<String,Object> model){
        System.out.println("Some text");
        return "redirect:/admin";
    }


    @PostMapping("/add/create-study-group")
    public String  createStudyGroup(StudyGroupRequestDto studyGroupRequestDto, Map<String,Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null){
//            model.put("message","Study group exists!");
            return "redirect:/admin/schedule/add/visiting";
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
        return "redirect:/admin/schedule/add/visiting";
    }


    @PostMapping("/add/create-position")
    public String  createPosition(PositionRequestDto positionRequestDto, Map<String,Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        //            model.put("message","Position exists!");
        if (positionFromDb != null) {
            return "redirect:/admin/schedule/add/visiting";
        }
        positionServices.addPosition(positionRequestDto.getPositionName());
        return "redirect:/admin/schedule/add/visiting";
    }


    @PostMapping("/add/create-discipline")
    public String  createDiscipline(AcademicDisciplineRequestDto disciplineRequestDto, Map<String,Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null){
//            model.put("message","Discipline exists!");
            return "redirect:/admin/schedule/add/visiting";
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
        return "redirect:/admin/schedule/add/visiting";
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
