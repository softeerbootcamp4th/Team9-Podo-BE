package com.softeer.podo.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softeer.podo.comment.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
