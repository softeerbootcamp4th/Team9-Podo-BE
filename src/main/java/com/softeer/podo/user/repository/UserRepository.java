package com.softeer.podo.user.repository;

import com.softeer.podo.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPhoneNum(String name, String phoneNum);
}
