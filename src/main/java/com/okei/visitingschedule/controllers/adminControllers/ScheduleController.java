package com.okei.visitingschedule.controllers.adminControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScheduleController {

    @GetMapping("/admin/schedule/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createSchedule(){
        return "createSchedule";
    }

    @PostMapping("/admin/schedule/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSchedule(){
        return "createSchedule";
    }
}
