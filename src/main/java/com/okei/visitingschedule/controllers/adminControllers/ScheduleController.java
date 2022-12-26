package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.ScheduleRequestDto;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.services.ScheduleServices;
import com.okei.visitingschedule.services.UserServices;
import com.okei.visitingschedule.services.VisitingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/schedule/")
public class ScheduleController {


    private UserServices userServices;
    private ScheduleServices scheduleServices;
    private VisitingServices visitingServices;

    @Autowired
    public ScheduleController(UserServices userServices,ScheduleServices scheduleServices,VisitingServices visitingServices) {
        this.userServices = userServices;
        this.scheduleServices = scheduleServices;
        this.visitingServices = visitingServices;
    }


    @GetMapping("add")
    public String addSchedule(Map<String, Object> model){
        Iterable<User> usersVisited = userServices.findByRoles(Role.USER_VISITED);
        Iterable<User> usersVisitor = userServices.findByRoles(Role.USER_VISITOR);

        model.put("usersVisited", usersVisited);
        model.put("usersVisitor", usersVisitor);
        return "createSchedule";
    }

    @PostMapping("add")
    public String createSchedule(ScheduleRequestDto scheduleRequestDto,Map<String ,Object> model){
        Schedule scheduleFromDb = scheduleServices.findFromDb(scheduleRequestDto.getVisitedUser(),
                scheduleRequestDto.getVisitorUser(),
                scheduleRequestDto.getVisitingWeek());
        if(scheduleFromDb == null){
            scheduleServices.addSchedule(scheduleRequestDto.getVisitedUser(),
                    scheduleRequestDto.getVisitorUser(),
                    scheduleRequestDto.getVisitingWeek());
            return "redirect:/home";
        }else return "redirect:/admin/schedule/add";
    }


}
