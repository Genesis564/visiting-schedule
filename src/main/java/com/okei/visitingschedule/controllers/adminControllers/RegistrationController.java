package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.dto.UserRequestDTO;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.services.PositionServices;
import com.okei.visitingschedule.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    private final UserServices userServices;
    private final PositionServices positionServices;

    @Autowired
    public RegistrationController(UserServices userServices,PositionServices positionServices) {
        this.userServices = userServices;
        this.positionServices = positionServices;
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model){
        Iterable<Position> positions = positionServices.findAll();

        model.put("positions", positions);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("positionId") Position position,
                          UserRequestDTO userRequestDTO,
                          Map<String,Object> model) {
        User userFromDb = userServices.findByUsername(userRequestDTO.getUsername());
        if (userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        if (userRequestDTO.getFirstname() == null && userRequestDTO.getLastname() == null){
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    Collections.singleton(Role.USER_VISITED));
        } else if (userRequestDTO.getMiddlename() == null){
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    Collections.singleton(Role.USER_VISITED),
                    userRequestDTO.getLastname(),
                    userRequestDTO.getFirstname());
        } else{
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    Collections.singleton(Role.USER_VISITED),
                    userRequestDTO.getLastname(),
                    userRequestDTO.getFirstname(),
                    userRequestDTO.getMiddlename());
        }
        return "redirect:/admin/registration";
    }


}
