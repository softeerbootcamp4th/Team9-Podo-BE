package com.softeer.podo.admin.repository;

import com.softeer.podo.admin.model.entity.Event;
import com.softeer.podo.admin.model.entity.EventReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRewardRepository extends JpaRepository<EventReward, Long> {
	List<EventReward> findByEvent (Event event);
}
