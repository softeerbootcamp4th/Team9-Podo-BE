package com.softeer.podo.admin.repository;

import com.softeer.podo.admin.model.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
