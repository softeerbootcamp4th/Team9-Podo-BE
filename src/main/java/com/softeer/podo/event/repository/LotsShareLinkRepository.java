package com.softeer.podo.event.repository;

import com.softeer.podo.admin.model.entity.LotsUser;
import com.softeer.podo.event.model.entity.LotsShareLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotsShareLinkRepository extends JpaRepository<LotsShareLink, Long> {
    Optional<LotsShareLink> findByLotsUser(LotsUser user);
}
