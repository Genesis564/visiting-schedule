package com.okei.visitingschedule.controllers.adminControllers;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.services.PositionServices;
import com.okei.visitingschedule.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserServices userServices;
    private final PositionServices positionServices;

    @Autowired
    public UserController(UserServices userServices,PositionServices positionServices) {
        this.userServices = userServices;
        this.positionServices = positionServices;
    }

    @GetMapping("/user-list")
    public String userList(Map<String, Object> model) {
        Iterable<User> users = userServices.findAll();
        model.put("users", users);
        model.put("roles", Role.values());
        return "userList";
    }

    @GetMapping("{user}")
    public void userEditForm(@PathVariable User user, Map<String, Object> model) {
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
        model.put("user", user);
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String lastname,
            @RequestParam String firstname,
            @RequestParam String middlename,
            @RequestParam String position,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        user.setUsername(username);
        user.setLastname(lastname);
        user.setFirstname(firstname);
        user.setMiddlename(middlename);

        Position positionFromDB = positionServices.findByPositionName(position);
        if (positionFromDB != null){
            user.setPosition(positionFromDB);
        }else {
            positionServices.addPosition(position);
            Position newPosition = positionServices.findByPositionName(position);
            user.setPosition(newPosition);
        }

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userServices.save(user);
        return "redirect:/admin/user-list";
    }
}
