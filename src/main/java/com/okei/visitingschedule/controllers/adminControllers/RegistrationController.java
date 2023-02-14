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

import java.util.*;
import java.util.stream.Collectors;

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
        Role[] roles = Role.values();

        model.put("roles", roles);
        model.put("positions", positions);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("positionId") Position position,
                          @RequestParam("roleName") Role role,
                          UserRequestDTO userRequestDTO,
                          Map<String,Object> model) {
        User userFromDb = userServices.findByUsername(userRequestDTO.getUsername());
        Set<Role> roleSet = new HashSet<>();
        if (role == Role.ADMIN){
            roleSet.add(Role.ADMIN);
            roleSet.add(Role.USER_VISITOR);
            roleSet.add(Role.USER_VISITED);
        } else if (role == Role.USER_VISITOR) {
            roleSet.add(Role.USER_VISITED);
            roleSet.add(Role.USER_VISITOR);
        }else {
            roleSet.add(Role.USER_VISITED);
        }
        if (userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        if (userRequestDTO.getFirstname() == null && userRequestDTO.getLastname() == null){
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    roleSet);
        } else if (userRequestDTO.getMiddlename() == null){
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    roleSet,
                    userRequestDTO.getLastname(),
                    userRequestDTO.getFirstname());
        } else{
            userServices.addUser(userRequestDTO.getUsername(),
                    userRequestDTO.getPassword(),
                    position,
                    roleSet,
                    userRequestDTO.getLastname(),
                    userRequestDTO.getFirstname(),
                    userRequestDTO.getMiddlename());
        }
        return "redirect:/admin/registration";
    }


}
