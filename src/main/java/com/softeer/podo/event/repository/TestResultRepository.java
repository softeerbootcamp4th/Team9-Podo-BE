package com.softeer.podo.event.repository;

import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface TestResultRepository  extends JpaRepository<TestResult, Long> {
    TestResult findByResult(Result result);
}
