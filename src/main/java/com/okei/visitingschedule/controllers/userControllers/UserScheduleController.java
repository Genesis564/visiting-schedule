package com.okei.visitingschedule.controllers.userControllers;

import com.okei.visitingschedule.dto.VisitingRequestDTO;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.*;
import com.okei.visitingschedule.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/schedule/")
public class UserScheduleController {

    private final AcademicDisciplineServices academicDisciplineServices;
    private final PositionServices positionServices;
    private final VisitingServices visitingServices;
    private final CriteriaScoreServices criteriaScoreServices;
    private final StudyGroupServices studyGroupServices;
    private final VisitingCriteriaService visitingCriteriaService;
    private final ScheduleServices scheduleServices;

    @Autowired
    public UserScheduleController(AcademicDisciplineServices academicDisciplineServices, PositionServices positionServices, VisitingServices visitingServices, StudyGroupServices studyGroupServices, VisitingCriteriaService visitingCriteriaService, CriteriaScoreServices criteriaScoreServices, ScheduleServices scheduleServices) {
        this.academicDisciplineServices = academicDisciplineServices;
        this.positionServices = positionServices;
        this.visitingServices = visitingServices;
        this.studyGroupServices = studyGroupServices;
        this.visitingCriteriaService = visitingCriteriaService;
        this.criteriaScoreServices = criteriaScoreServices;
        this.scheduleServices = scheduleServices;
    }

    @GetMapping("add/{schedule}")
    public String addVisiting(@PathVariable Schedule schedule, Map<String, Object> model) {
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();
        Iterable<VisitingCriteria> visitingCriteria = visitingCriteriaService.findAll();

        model.put("schedule", schedule);
        model.put("statuses", Status.values());
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
        model.put("criteries", visitingCriteria);
        return "createVisiting";
    }

    @GetMapping("view/{schedule}")
    public String viewVisiting(@PathVariable Schedule schedule, Principal principal, Map<String, Object> model) {
        Visiting thisVisiting = visitingServices.findVisitingBySchedule(schedule);
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Set<CriteriaScore> criteriaScore = thisVisiting.getCriteriaScore();

        List<CriteriaScore> sortedCriteriaScore = criteriaScoreServices.sortCriteria(criteriaScore);
        boolean access = false;
        if ((schedule.getVisitedUser().equals(user) || user.isAdmin())
                && schedule.getStatus().contains(Status.WAITING_TO_CONFIRM)) {
            access = true;
        }

        boolean subjectToEditing = false;
        if ((schedule.getVisitorUser().equals(user) || user.isAdmin())
                && schedule.getStatus().contains(Status.WAITING_TO_CONFIRM)) {
            subjectToEditing = true;
        }

        List<VisitingCriteria> visitingCriteria = new ArrayList<>();
        for (CriteriaScore score : sortedCriteriaScore) {
            visitingCriteria.add(visitingCriteriaService.findById(score.getVisitingCriteria().getId()));
        }

        model.put("access", access);
        model.put("subjectToEditing", subjectToEditing);
        model.put("criteriaScore", sortedCriteriaScore);
        model.put("criteries", visitingCriteria);
        model.put("visiting", thisVisiting);
        return "visiting";
    }

    @GetMapping("edit/{schedule}")
    public String editVisiting(@PathVariable Schedule schedule, Map<String, Object> model) {
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();
        Visiting thisVisiting = visitingServices.findVisitingBySchedule(schedule);

        Set<CriteriaScore> criteriaScore = thisVisiting.getCriteriaScore();
        List<CriteriaScore> sortedCriteriaName = new ArrayList<>(criteriaScoreServices.sortCriteria(criteriaScore));

        List<VisitingCriteria> visitingCriteria = new ArrayList<>();
        for (CriteriaScore score : sortedCriteriaName) {
            visitingCriteria.add(visitingCriteriaService.findById(score.getVisitingCriteria().getId()));
        }

        model.put("criteriaScore", sortedCriteriaName);
        model.put("criteries", visitingCriteria);
        model.put("visiting", thisVisiting);
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
        model.put("criteries", visitingCriteria);
        return "editVisiting";
    }

    @PostMapping("edit/{schedule}")
    public String saveEditedVisiting(@RequestParam("studyGroupId") StudyGroup studyGroup,
                                     @RequestParam("academicDisciplineId") AcademicDiscipline academicDiscipline,
                                     @RequestParam("positionId") Position position,
                                     @RequestParam("visitingId") Visiting visiting,
                                     VisitingRequestDTO visitingRequestDTO,
                                     Map<String, Object> model) {
        Set<CriteriaScore> criteriaScoreSet = visiting.getCriteriaScore();
        List<CriteriaScore> sortedCriteriaScore = criteriaScoreServices.sortCriteria(criteriaScoreSet);

        int i = 0;
        for (Integer criteriaScore : visitingRequestDTO.getCriteriaScoreIds()) {
            sortedCriteriaScore.get(i).setScore(criteriaScore);
            criteriaScoreServices.save(sortedCriteriaScore.get(i));
            i++;
        }

        new Visiting();
        visiting.setPurposeOfTheVisit(visitingRequestDTO.getPurposeOfTheVisit());
        visiting.setNumberOfStudents(visitingRequestDTO.getNumberOfStudents());
        visiting.setLessonTopic(visitingRequestDTO.getLessonTopic());
        visiting.setDate(visitingRequestDTO.getDate());
        visiting.setStudyGroup(studyGroup);
        visiting.setPosition(position);
        visiting.setAcademicDiscipline(academicDiscipline);
        visitingServices.save(visiting);
        return "redirect:/schedule/view/"+visiting.getSchedule().getId();
    }

    @PostMapping("view/{schedule}")
    public String confirmationVisiting(@PathVariable Schedule schedule, Map<String, Object> model) {
        Set<Status> statusSet = new HashSet<>();
        statusSet.add(Status.CONFIRMED);
        schedule.setStatus(statusSet);
        scheduleServices.save(schedule);
        return "redirect:/home";
    }

    @Transactional
    @PostMapping("add/visiting")
    public String createSchedule(@RequestParam("studyGroupId") StudyGroup studyGroup,
                                 @RequestParam("scheduleId") Schedule schedule,
                                 @RequestParam("academicDisciplineId") AcademicDiscipline academicDiscipline,
                                 @RequestParam("positionId") Position position,
                                 VisitingRequestDTO visitingRequestDTO, Map<String, Object> model) {
        Visiting visitingFromDb = visitingServices.findFromDb(schedule, visitingRequestDTO.getDate());
        List<VisitingCriteria> criteriaLists = new ArrayList<>();
        Set<Status> statusSet = new HashSet<>();
        statusSet.add(Status.WAITING_TO_CONFIRM);
        for (Long criteriaId : visitingRequestDTO.getCriterionIds()) {
            criteriaLists.add(visitingCriteriaService.findById(criteriaId));
        }


        if (visitingFromDb == null) {
            visitingServices.addVisiting(visitingRequestDTO.getPurposeOfTheVisit(),
                    visitingRequestDTO.getNumberOfStudents(),
                    visitingRequestDTO.getLessonTopic(),
                    visitingRequestDTO.getPurposeOfTheLesson(),
                    visitingRequestDTO.getDate(), studyGroup,
                    position, academicDiscipline,
                    criteriaLists,
                    schedule);
            visitingFromDb = visitingServices.findFromDb(schedule, visitingRequestDTO.getDate());
            schedule.setStatus(statusSet);
            schedule.setVisiting(visitingFromDb);
            scheduleServices.save(schedule);
            int i = 0;
            for (Integer criteriaScore : visitingRequestDTO.getCriteriaScoreIds()) {
                CriteriaScoreKey scoreKey = new CriteriaScoreKey(visitingFromDb.getId(), criteriaLists.get(i).getId());

                CriteriaScore newCriteriaScore = new CriteriaScore(scoreKey, visitingFromDb, criteriaLists.get(i), criteriaScore.intValue());
                criteriaScoreServices.save(newCriteriaScore);
                i++;
            }
            return "redirect:/home";
        }

        return "redirect:/schedule/add/" + schedule.getId();

    }
}
