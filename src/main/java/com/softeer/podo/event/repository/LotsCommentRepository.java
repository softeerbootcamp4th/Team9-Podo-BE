package com.softeer.podo.event.repository;

import com.softeer.podo.user.model.entity.LotsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import com.softeer.podo.event.model.entity.LotsComment;

public interface LotsCommentRepository extends JpaRepository<LotsComment, Long> {
    boolean existsByUser(LotsUser lotsUser);
}
