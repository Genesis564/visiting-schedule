package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.AcademicDisciplineRequestDto;
import com.okei.visitingschedule.dto.PositionRequestDto;
import com.okei.visitingschedule.dto.StudyGroupRequestDto;
import com.okei.visitingschedule.dto.VisitingCriteriaRequestDTO;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.services.AcademicDisciplineServices;
import com.okei.visitingschedule.services.PositionServices;
import com.okei.visitingschedule.services.StudyGroupServices;
import com.okei.visitingschedule.services.VisitingCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/")
public class VisitingComponentsController {
    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final StudyGroupServices studyGroupServices;
    private final VisitingCriteriaService visitingCriteriaService;

    @Autowired
    public VisitingComponentsController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
    }

    @GetMapping
    public String editComponents(Map<String, Object> model) {
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();

        model.put("academicDisciplines", academicDisciplines);
        model.put("positions", positions);
        model.put("studyGroups", studyGroups);
        return "editComponents";
    }

    @PostMapping("add/create-study-group")
    public ResponseEntity createStudyGroup(StudyGroupRequestDto studyGroupRequestDto, Map<String, Object> model) {
        StudyGroup studyGroupFromDb = studyGroupServices.findByGroupName(studyGroupRequestDto.getGroupName());
        if (studyGroupFromDb != null) {
//            model.put("message","Study group exists!");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        studyGroupServices.addStudyGroup(studyGroupRequestDto.getGroupName());
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("add/create-position")
    @ResponseBody
    public ResponseEntity<?> createPosition(PositionRequestDto positionRequestDto, Map<String, Object> model) {
        Position positionFromDb = positionServices.findByPositionName(positionRequestDto.getPositionName());
        //            model.put("message","Position exists!");
        if (positionFromDb != null) {
            return new ResponseEntity(positionFromDb, HttpStatus.OK);
        }
        positionServices.addPosition(positionRequestDto.getPositionName());
        return new ResponseEntity(positionServices.findByPositionName(positionRequestDto.getPositionName()), HttpStatus.OK);
    }

    @PostMapping("add/create-discipline")
    public ResponseEntity createDiscipline(AcademicDisciplineRequestDto disciplineRequestDto, Map<String, Object> model) {
        AcademicDiscipline disciplineFromDb = academicDisciplineServices.findByDisciplineName(disciplineRequestDto.getDisciplineName());
        if (disciplineFromDb != null) {
//            model.put("message","Discipline exists!");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        academicDisciplineServices.addDiscipline(disciplineRequestDto.getDisciplineName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("add/create-visiting-criteria")
    public ResponseEntity createCriteria(@RequestParam("scheduleId") long scheduleId, VisitingCriteriaRequestDTO visitingCriteriaRequestDTO, Map<String, Object> model) {
        VisitingCriteria visitingCriteriaFromDb = visitingCriteriaService.findByCriteriaName(visitingCriteriaRequestDTO.getCritariaName());
        if (visitingCriteriaFromDb != null) {
//            model.put("message","Discipline exists!");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        visitingCriteriaService.addCriteria(visitingCriteriaRequestDTO.getCritariaName(),
                visitingCriteriaRequestDTO.getValueOfOnePoint(),
                visitingCriteriaRequestDTO.getValueOfTwoPoint(),
                visitingCriteriaRequestDTO.getValueOfThreePoint());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("edit/position/")
    public ResponseEntity editPosition(@RequestParam("positionId") Position position, PositionRequestDto positionRequestDto, Map<String, Object> model) {

        position.setPositionName(positionRequestDto.getPositionName());
        positionServices.save(position);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("delete/position/{position}")
    public ResponseEntity deletePosition(@PathVariable Position position){
        try {
            positionServices.delete(position);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("edit/academic-discipline/")
    public ResponseEntity editAcademicDiscipline(@RequestParam("academicDisciplineId") AcademicDiscipline academicDiscipline,
                                                 AcademicDisciplineRequestDto academicDisciplineRequestDto, Map<String, Object> model) {

        academicDiscipline.setDisciplineName(academicDisciplineRequestDto.getDisciplineName());
        academicDisciplineServices.save(academicDiscipline);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("delete/academic-discipline/{academicDiscipline}")
    public ResponseEntity deleteAcademicDiscipline(@PathVariable AcademicDiscipline academicDiscipline){
        try {
            academicDisciplineServices.delete(academicDiscipline);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("edit/study-group/")
    public ResponseEntity editStudyGroup(@RequestParam("studyGroupId") StudyGroup studyGroup,
                                                 StudyGroupRequestDto studyGroupRequestDto, Map<String, Object> model) {

        studyGroup.setGroupName(studyGroupRequestDto.getGroupName());
        studyGroupServices.save(studyGroup);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("delete/study-group/{studyGroup}")
    public ResponseEntity deleteStudyGroup(@PathVariable StudyGroup studyGroup){
        try {
            studyGroupServices.delete(studyGroup);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
