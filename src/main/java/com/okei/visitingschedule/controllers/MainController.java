package com.okei.visitingschedule.controllers;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Event;
import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import com.okei.visitingschedule.services.ScheduleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

@Controller
public class MainController {

    private final ScheduleServices scheduleServices;

    @Autowired
    public MainController(ScheduleServices scheduleServices) {
        this.scheduleServices = scheduleServices;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String main(Principal principal, Map<String, Object> model) {
        scheduleServices.updateScheduleStatus();
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Set<Role> roles = user.getRoles();
        List<Schedule> schedulesWaitingToConfirm = null;
        List<Schedule> schedulesWaitingToConfirmEvent = null;
        List<Schedule> schedulesPlanned = null;
        List<Schedule> schedulesConfirmed = null;
        List<Schedule> schedulesOverdue = null;
        List<Schedule> schedulesSummingUp = null;
        if (roles.contains(Role.ADMIN)) {
            schedulesWaitingToConfirm = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM);
            schedulesWaitingToConfirmEvent = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM_EVENT);
            schedulesPlanned = scheduleServices.findAllByStatus(Status.PLANNED);
            schedulesConfirmed = scheduleServices.findAllByStatus(Status.CONFIRMED);
            schedulesOverdue = scheduleServices.findAllByStatus(Status.OVERDUE);
            schedulesSummingUp = scheduleServices.findAllByStatus(Status.SUMMING_UP);
        } else if (roles.contains(Role.USER_VISITOR) || roles.contains(Role.USER_VISITED)) {
            schedulesWaitingToConfirm = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM,user);
            schedulesWaitingToConfirmEvent = scheduleServices.findAllByStatus(Status.WAITING_TO_CONFIRM_EVENT,user);
            schedulesPlanned = scheduleServices.findAllByStatus(Status.PLANNED,user);
            schedulesConfirmed = scheduleServices.findAllByStatus(Status.CONFIRMED,user);
            schedulesOverdue = scheduleServices.findAllByStatus(Status.OVERDUE,user);
            schedulesSummingUp = scheduleServices.findAllByStatus(Status.SUMMING_UP,user);
        }

        boolean[] accessToConfirmVisiting = new boolean[schedulesWaitingToConfirm.size()];
        int i = 0;
        int badgeCount = 0;
        for (Schedule schedule : schedulesWaitingToConfirm) {

            if ((schedule.getVisitedUser().equals(user) || user.isAdmin())) {
                accessToConfirmVisiting[i] = true;
                badgeCount++;
            } else {
                accessToConfirmVisiting[i] = false;
            }
            i++;
        }

        boolean[] accessToConfirmEvent = new boolean[schedulesWaitingToConfirmEvent.size()];
        i = 0;
        for (Schedule schedule : schedulesWaitingToConfirmEvent) {
            if ((schedule.getVisitorUser().equals(user) || user.isAdmin())) {
                accessToConfirmEvent[i] = true;
                badgeCount++;
            } else {
                accessToConfirmEvent[i] = false;
            }
            i++;
        }

        boolean[] accessToSummingUpVisiting = new boolean[schedulesSummingUp.size()];
        i = 0;
        for (Schedule schedule : schedulesSummingUp) {
            if ((schedule.getVisitorUser().equals(user) || user.isAdmin())) {
                accessToSummingUpVisiting[i] = true;
            } else {
                accessToSummingUpVisiting[i] = false;
            }
            i++;
        }

        boolean[] accessToCreateVisiting = new boolean[schedulesPlanned.size()];
        i = 0;
        Calendar date = new GregorianCalendar();
        int week = date.get(Calendar.WEEK_OF_YEAR);
        int year = date.get(Calendar.YEAR);
        for (Schedule schedule : schedulesPlanned) {
            int scheduleWeek = Integer.parseInt(schedule.getVisitingWeek().substring(6));
            int scheduleYear = Integer.parseInt(schedule.getVisitingWeek().substring(0, 4));

            if ((schedule.getVisitorUser().equals(user) || user.isAdmin()) && (scheduleWeek == week && scheduleYear == year)) {
                accessToCreateVisiting[i] = true;
            } else {
                accessToCreateVisiting[i] = false;
            }
            i++;
        }

        int[] notConfirmedEventSize = new int[schedulesConfirmed.size()];
        int size = 0;
        i=0;
        for (Schedule schedule:schedulesConfirmed) {
            for (Event event:schedule.getVisiting().getConclusion().getEvents()) {
                if (!event.isCompletionMark()){
                    ++size;
                }
            }
            notConfirmedEventSize[i] = size;
            size = 0;
            i++;
        }

        boolean[] isEmpty = new boolean[]{schedulesSummingUp.isEmpty(), schedulesWaitingToConfirm.isEmpty(), schedulesPlanned.isEmpty(), schedulesConfirmed.isEmpty(), schedulesOverdue.isEmpty()};


        model.put("schedulesWaitingToConfirm", schedulesWaitingToConfirm);
        model.put("schedulesWaitingToConfirmEvent", schedulesWaitingToConfirmEvent);
        model.put("notConfirmedEventSize", notConfirmedEventSize);
        model.put("schedulesPlanned", schedulesPlanned);
        model.put("schedulesConfirmed", schedulesConfirmed);
        model.put("schedulesOverdue", schedulesOverdue);
        model.put("schedulesSummingUp", schedulesSummingUp);
        model.put("isEmpty", isEmpty);
        model.put("badgeCount", badgeCount);
        model.put("accessToConfirmVisiting", accessToConfirmVisiting);
        model.put("accessToConfirmEvent", accessToConfirmEvent);
        model.put("accessToCreateVisiting", accessToCreateVisiting);
        model.put("accessToSummingUpVisiting", accessToSummingUpVisiting);
        return "main";
    }
}
