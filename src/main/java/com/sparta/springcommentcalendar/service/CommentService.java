package com.sparta.springcommentcalendar.service;

import com.sparta.springcommentcalendar.dto.CommentDTO;
import com.sparta.springcommentcalendar.entity.Comment;
import com.sparta.springcommentcalendar.entity.Schedule;
import com.sparta.springcommentcalendar.entity.User;
import com.sparta.springcommentcalendar.repository.CommentRepository;
import com.sparta.springcommentcalendar.repository.ScheduleRepository;
import com.sparta.springcommentcalendar.repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment createComment(Long scheduleId, CommentDTO commentDTO, String username) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setSchedule(schedule);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        return schedule.getComments();
    }

    @Transactional
    public Comment updateComment(Long scheduleId, Long commentId, CommentDTO commentDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (!comment.getSchedule().equals(schedule)) {
            throw new IllegalArgumentException("Comment does not belong to the given schedule");
        }

        comment.setContent(commentDTO.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        return comment;
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (!comment.getSchedule().equals(schedule)) {
            throw new IllegalArgumentException("Comment does not belong to the given schedule");
        }

        commentRepository.delete(comment);
    }
}
