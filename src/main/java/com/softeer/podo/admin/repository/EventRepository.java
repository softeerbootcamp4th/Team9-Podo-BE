package com.softeer.podo.admin.repository;

import com.softeer.podo.admin.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
