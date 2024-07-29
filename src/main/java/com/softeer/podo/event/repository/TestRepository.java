package com.softeer.podo.event.repository;

import com.softeer.podo.event.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
