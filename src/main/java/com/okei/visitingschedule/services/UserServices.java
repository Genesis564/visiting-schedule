package com.okei.visitingschedule.services;

import com.okei.visitingschedule.entity.Role;
import com.okei.visitingschedule.entity.User;
import com.okei.visitingschedule.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServices implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Autowired
    public UserServices(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public void addUser(String username, String password, Set<Role> roles){
        User user = new User(username,password,true,roles);
        usersRepo.save(user);
    }

    public void save(User user){
        usersRepo.save(user);
    }

    public List<User> findAll(){
       return (List<User>) usersRepo.findAll();
    };

    public User findByUsername(String username){
        return usersRepo.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepo.findByUsername(username);
    }
}
