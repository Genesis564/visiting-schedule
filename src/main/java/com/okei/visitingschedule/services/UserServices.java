package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.entity.schedule.Position;
import com.okei.visitingschedule.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServices implements UserDetailsService {

    private final UsersRepo usersRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UsersRepo usersRepo,PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(String username, String password, Position position, Set<Role> roles){
        User user = new User(username,passwordEncoder.encode(password),true,position,roles);
        usersRepo.save(user);
    }

    public void addUser(String username, String password,Position position, Set<Role> roles,String lastname,String firstname){
        User user = new User(username,passwordEncoder.encode(password),true,position,roles,lastname,firstname);
        usersRepo.save(user);
    }
    public void addUser(String username, String password,Position position, Set<Role> roles,String lastname,String firstname,String middlename){
        User user = new User(username,passwordEncoder.encode(password),true,position,roles,lastname,firstname,middlename);
        usersRepo.save(user);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
    }

    public List<User> findAll(){
       return (List<User>) usersRepo.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    };

    public List<User> findByRoles(Role role){
        return usersRepo.findUsersByRoles(role);
    }

    public User findByUsername(String username){
        return usersRepo.findByUsername(username).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found"));

        return user;
    }
}
