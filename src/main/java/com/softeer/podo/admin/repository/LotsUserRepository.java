package com.softeer.podo.admin.repository;

import com.softeer.podo.admin.model.entity.LotsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotsUserRepository extends JpaRepository<LotsUser, Long> {
    LotsUser findByNameAndPhoneNum(String name, String phoneNum);
    boolean existsByPhoneNum(String phoneNum);
}
