package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.UserRequestDTO;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    private final UserServices userServices;

    @Autowired
    public RegistrationController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserRequestDTO userRequestDTO, Map<String,Object> model) {
        User userFromDb = userServices.findByUsername(userRequestDTO.getUsername());
        if (userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        userServices.addUser(userRequestDTO.getUsername(), userRequestDTO.getPassword(),Collections.singleton(Role.USER_VISITED));
        return "redirect:/admin/registration";

    }
}
