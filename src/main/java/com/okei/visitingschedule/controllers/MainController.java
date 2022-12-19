package com.okei.visitingschedule.controllers;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    private final UserServices userServices;

    @Autowired
    public MainController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/home")
    public String main(Map<String, Object> model) {
        Iterable<User> usersVisited = userServices.findByRoles(Role.USER_VISITED);
        Iterable<User> usersVisitor = userServices.findByRoles(Role.USER_VISITOR);

        model.put("usersVisited", usersVisited);
        model.put("usersVisitor", usersVisitor);
        return "main";
    }
}
