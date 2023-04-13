package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.services.ScheduleServices;
import com.okei.visitingschedule.util.ExcelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/report/")
public class ReportController {

    private final ScheduleServices scheduleServices;

    @Autowired
    public ReportController(ScheduleServices scheduleServices) {
        this.scheduleServices = scheduleServices;
    }

    @GetMapping
    public String getReport(Map<String,Object> model){
        List<Schedule> schedules = new ArrayList<>(scheduleServices.findAll());
        model.put("schedules",schedules);
        return "reports";
    }
    @GetMapping("/excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=schedule" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Schedule> listOfStudents = scheduleServices.findAll();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }
}
