package com.okei.visitingschedule.controllers;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Visiting;
import com.okei.visitingschedule.services.ScheduleServices;
import com.okei.visitingschedule.services.UserServices;
import com.okei.visitingschedule.services.VisitingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {
    private final UserServices userServices;
    private final VisitingServices visitingServices;
    private final ScheduleServices scheduleServices;

    @Autowired
    public MainController(UserServices userServices, ScheduleServices scheduleServices, VisitingServices visitingServices) {
        this.userServices = userServices;
        this.scheduleServices = scheduleServices;
        this.visitingServices = visitingServices;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/home")
    public String main(Principal principal,Map<String, Object> model) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Set<Role> roles = user.getRoles();
        Iterable<Schedule> schedules = null;
        if (roles.contains(Role.ADMIN)){
            schedules = scheduleServices.findAll();
        } else if (roles.contains(Role.USER_VISITOR) || roles.contains(Role.USER_VISITED)) {
            schedules = scheduleServices.findByUser(user);
        }
        Iterable<Visiting> visitings = visitingServices.findAll();

        model.put("visitings", visitings);
        model.put("schedules", schedules);
        scheduleServices.updateScheduleStatus();
        return "main";
    }
}
