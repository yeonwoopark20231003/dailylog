package com.sparta.dailylog.comment.repository;

import com.sparta.dailylog.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
