package com.okei.visitingschedule.repos;

import com.okei.visitingschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Long> {
        User findByUsername(String username);
}
