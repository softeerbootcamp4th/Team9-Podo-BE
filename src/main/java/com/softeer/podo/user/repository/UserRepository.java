package com.softeer.podo.user.repository;

import com.softeer.podo.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPhoneNum(String name, String phoneNum);
    boolean existsByPhoneNum(String phoneNum);
}
