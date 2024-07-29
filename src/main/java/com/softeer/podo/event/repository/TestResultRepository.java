package com.softeer.podo.event.repository;

import com.softeer.podo.event.model.entity.TestResult;
import org.springframework.data.repository.CrudRepository;

public interface TestResultRepository  extends CrudRepository<TestResult, Long> {
}
