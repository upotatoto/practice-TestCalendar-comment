package com.sparta.springcommentcalendar.repository;

import com.sparta.springcommentcalendar.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
