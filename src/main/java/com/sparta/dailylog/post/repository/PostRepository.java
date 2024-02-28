package com.sparta.dailylog.post.repository;

import com.sparta.dailylog.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
