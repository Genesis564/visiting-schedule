package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.entity.schedule.AcademicDiscipline;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.entity.schedule.Status;
import com.okei.visitingschedule.entity.schedule.StudyGroup;
import com.okei.visitingschedule.services.AcademicDisciplineServices;
import com.okei.visitingschedule.services.PositionServices;
import com.okei.visitingschedule.services.ScheduleServices;
import com.okei.visitingschedule.services.StudyGroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createSchedule(Map<String, Object> model){
        Iterable<StudyGroup> studyGroups = studyGroupServices.findAll();
        Iterable<Position> positions = positionServices.findAll();
        Iterable<AcademicDiscipline> academicDisciplines = academicDisciplineServices.findAll();


        model.put("statuses", Status.values());
        model.put("studyGroups", studyGroups);
        model.put("positions", positions);
        model.put("academicDisciplins", academicDisciplines);
//        model.put("criteries", Criteria);
        return "createSchedule";
    }

    @PostMapping("/admin/schedule/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSchedule(){
        return "createSchedule";
    }
}
