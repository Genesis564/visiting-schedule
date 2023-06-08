package com.okei.visitingschedule.services;

import com.okei.visitingschedule.Application;
import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.repos.UsersRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserServicesTest {

    @Autowired
    private UserServices userServices;

    @MockBean
    private UsersRepo usersRepo;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUserWithoutFio() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER_VISITED);
        Position position = new Position();
        boolean isUserCreated = userServices.addUser("username","qwerty123",position,roles);
        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void addUserWithoutMidlename() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER_VISITED);
        Position position = new Position();
        boolean isUserCreated = userServices.addUser("username","qwerty123",position,roles,"Иванов","Иван");
        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void addUserWithAllData() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER_VISITED);
        Position position = new Position();
        boolean isUserCreated = userServices.addUser("username","qwerty123",position,roles,"Иванов","Иван","Иванович");
        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void saveUser() {
        User user = new User();
        userServices.save(user);
        Mockito.verify(usersRepo,Mockito.times(1)).save(user);
    }

    @Test
    public void findAllUsers() {
        List<User> users= userServices.findAll();
        Assert.assertNotNull(users);
        Mockito.verify(usersRepo,Mockito.times(1)).findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Test
    public void findUserByRoles() {
        Role role = Role.USER_VISITED;
        List<User> users = userServices.findByRoles(role);
        Assert.assertNotNull(users);
        Mockito.verify(usersRepo,Mockito.times(1)).findUsersByRoles(role);
    }

    @Test
    public void encodePasswordByUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER_VISITED);
        Position position = new Position();
        User user = new User("username","qwerty123",true,position,roles);
        userServices.save(user);
        Mockito.verify(passwordEncoder,Mockito.times(1)).encode("qwerty123");
    }
}