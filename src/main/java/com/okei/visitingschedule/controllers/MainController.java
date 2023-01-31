package com.okei.visitingschedule.controllers;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final ScheduleServices scheduleServices;

    @Autowired
    public MainController(ScheduleServices scheduleServices) {
        this.scheduleServices = scheduleServices;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/home")
    public String main(Principal principal, Map<String, Object> model) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Set<Role> roles = user.getRoles();
        List<Schedule> schedulesWaitingToConfirm = null;
        List<Schedule> schedulesPlanned = null;
        List<Schedule> schedulesConfirmed = null;
        List<Schedule> schedulesOverdue = null;
        if (roles.contains(Role.ADMIN)) {
            schedulesWaitingToConfirm = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM);
            schedulesPlanned = scheduleServices.findAllByStatus(Status.PLANNED);
            schedulesConfirmed = scheduleServices.findAllByStatus(Status.CONFIRMED);
            schedulesOverdue = scheduleServices.findAllByStatus(Status.OVERDUE);
        } else if (roles.contains(Role.USER_VISITOR) || roles.contains(Role.USER_VISITED)) {
            schedulesWaitingToConfirm = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM,user);
            schedulesPlanned = scheduleServices.findAllByStatus(Status.PLANNED,user);
            schedulesConfirmed = scheduleServices.findAllByStatus(Status.CONFIRMED,user);
            schedulesOverdue = scheduleServices.findAllByStatus(Status.OVERDUE,user);
        }

        boolean[] accessToConfirmVisiting = new boolean[schedulesWaitingToConfirm.size()];
        int i = 0;
        for (Schedule schedule:schedulesWaitingToConfirm) {

            if ((schedule.getVisitedUser().equals(user) || user.isAdmin())){
                accessToConfirmVisiting[i]=true;
            }else {
                accessToConfirmVisiting[i]=false;
            }
            i++;
        }

        boolean[] accessToCreateVisiting = new boolean[schedulesPlanned.size()];
        i = 0;
        for (Schedule schedule:schedulesPlanned) {

            if ((schedule.getVisitorUser().equals(user) || user.isAdmin())){
                accessToCreateVisiting[i]=true;
            }else {
                accessToCreateVisiting[i]=false;
            }
            i++;
        }

        boolean[] isEmpty = new boolean[] {schedulesWaitingToConfirm.isEmpty(),schedulesPlanned.isEmpty(),schedulesConfirmed.isEmpty(),schedulesOverdue.isEmpty()};


        model.put("schedulesWaitingToConfirm", schedulesWaitingToConfirm);
        model.put("schedulesPlanned", schedulesPlanned);
        model.put("schedulesConfirmed", schedulesConfirmed);
        model.put("schedulesOverdue", schedulesOverdue);
        model.put("isEmpty",isEmpty);
        model.put("accessToConfirmVisiting",accessToConfirmVisiting);
        model.put("accessToCreateVisiting",accessToCreateVisiting);
        scheduleServices.updateScheduleStatus();
        return "main";
    }
}
