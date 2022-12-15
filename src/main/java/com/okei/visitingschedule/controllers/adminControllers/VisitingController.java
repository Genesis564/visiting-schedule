package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.*;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.repos.CriteriaScoreRepo;
import com.okei.visitingschedule.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/schedule/add/")
public class VisitingController {
    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final VisitingServices visitingServices;
    private final CriteriaScoreRepo criteriaScoreRepo;
    private final StudyGroupServices studyGroupServices;
    private final VisitingCriteriaService visitingCriteriaService;

    @Autowired
    public VisitingController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, VisitingServices visitingServices, StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService, CriteriaScoreRepo criteriaScoreRepo) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.visitingServices = visitingServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
        this.criteriaScoreRepo = criteriaScoreRepo;
    }


    @GetMapping("{schedule}")
    public String addVisiting(@PathVariable Schedule schedule, Map<String, Object> model){
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();
        Iterable<VisitingCriteria> visitingCriteria = visitingCriteriaService.findAll();

        model.put("schedule",schedule);
        model.put("statuses", Status.values());
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
        model.put("criteries", visitingCriteria);
        return "createVisiting";
    }

    @PostMapping("visiting")
    public String createSchedule(@RequestParam("studyGroupId") StudyGroup studyGroup,
                                 @RequestParam("scheduleId") Schedule schedule,
                                 @RequestParam("academicDisciplineId") AcademicDiscipline academicDiscipline,
                                 @RequestParam("positionId") Position position,
                                 VisitingRequestDTO visitingRequestDTO, Map<String,Object> model){
        Visiting visitingFromDb = visitingServices.findFromDb(schedule, visitingRequestDTO.getDate());
        List<VisitingCriteria> criteriaLists = new ArrayList<>();
        Set<CriteriaScore> criteriaScoreSet = new HashSet<>();
        for (Long criteriaId : visitingRequestDTO.getCriterionIds()) {
            criteriaLists.add(visitingCriteriaService.findById(criteriaId));
        }

        if(visitingFromDb == null){
            visitingServices.addVisiting(visitingRequestDTO.getPurposeOfTheVisit(),
                    visitingRequestDTO.getNumberOfStudents(),
                    visitingRequestDTO.getLessonTopic(),
                    visitingRequestDTO.getPurposeOfTheLesson(),
                    visitingRequestDTO.getDate(),studyGroup,
                    position,academicDiscipline,
                    criteriaLists,
                    schedule);
            visitingFromDb = visitingServices.findFromDb(schedule, visitingRequestDTO.getDate());
            Set<CriteriaScore> tempCriteriaScoreSet = new HashSet<>();
            int i =0;
            for (Integer criteriaScore : visitingRequestDTO.getCriteriaScoreIds()) {
                CriteriaScoreKey scoreKey = new CriteriaScoreKey(visitingFromDb.getId(),criteriaLists.get(i).getId());
                CriteriaScore newCriteriaScore = new CriteriaScore(scoreKey,visitingFromDb, criteriaLists.get(i), criteriaScore.intValue());
                 criteriaScoreRepo.save(newCriteriaScore);
                i++;
//                tempCriteriaScoreSet.clear();
//                tempCriteriaScoreSet.add(newCriteriaScore);
//                criteriaLists.get(i).setCriteriaScore(tempCriteriaScoreSet);
//                visitingCriteriaService.updateCriteriaScore(criteriaLists.get(i),tempCriteriaScoreSet );
//                criteriaScoreSet.add(newCriteriaScore);
            }
            return "redirect:/admin/schedule/";
        }

        return "redirect:/admin/schedule/add";
    }


    @PostMapping("create-study-group")
    public String  createStudyGroup(@RequestParam("scheduleId") long scheduleId,StudyGroupRequestDto studyGroupRequestDto, Map<String,Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null){
//            model.put("message","Study group exists!");
            return "redirect:/admin/schedule/add/" + scheduleId;
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
        return "redirect:/admin/schedule/add/" + scheduleId;
    }


    @PostMapping("create-position")
    public String  createPosition(@RequestParam("scheduleId") long scheduleId,PositionRequestDto positionRequestDto, Map<String,Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        //            model.put("message","Position exists!");
        if (positionFromDb != null) {
            return "redirect:/admin/schedule/add/" + scheduleId;
        }
        positionServices.addPosition(positionRequestDto.getPositionName());
        return "redirect:/admin/schedule/add/" + scheduleId;
    }


    @PostMapping("create-discipline")
    public String  createDiscipline(@RequestParam("scheduleId") long scheduleId,AcademicDisciplineRequestDto disciplineRequestDto, Map<String,Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null){
//            model.put("message","Discipline exists!");
            return "redirect:/admin/schedule/add/" + scheduleId;
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
        return "redirect:/admin/schedule/add/" + scheduleId;
    }

    @PostMapping("create-visiting-criteria")
    public String  createCriteria(@RequestParam("scheduleId") long scheduleId,VisitingCriteriaRequestDTO visitingCriteriaRequestDTO, Map<String,Object> model){
        VisitingCriteria visitingCriteriaFromDb = visitingCriteriaService.findByCriteriaName(visitingCriteriaRequestDTO.getCritariaName());
        if (visitingCriteriaFromDb != null){
//            model.put("me ssage","Discipline exists!");
            return "redirect:/admin/schedule/add/" + scheduleId;
        }
        visitingCriteriaService.addCriteria(visitingCriteriaRequestDTO.getCritariaName(),
                visitingCriteriaRequestDTO.getValueOfOnePoint(),
                visitingCriteriaRequestDTO.getValueOfTwoPoint(),
                visitingCriteriaRequestDTO.getValueOfThreePoint());
        return "redirect:/admin/schedule/add/" + scheduleId;
    }

}
