package com.softeer.podo.user.repository;

import com.softeer.podo.user.model.entity.LotsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotsUserRepository extends JpaRepository<LotsUser, Long> {
    LotsUser findByNameAndPhoneNum(String name, String phoneNum);
    boolean existsByPhoneNum(String phoneNum);
}
