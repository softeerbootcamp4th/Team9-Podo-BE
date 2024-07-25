package com.softeer.podo.test.repository;

import com.softeer.podo.user.model.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestUser, Long> {

}
