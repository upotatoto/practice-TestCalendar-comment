package com.sparta.springcommentcalendar.controller;

import com.sparta.springcommentcalendar.dto.CommentDTO;
import com.sparta.springcommentcalendar.entity.Comment;
import com.sparta.springcommentcalendar.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long scheduleId, @RequestBody CommentDTO commentDTO, @RequestHeader("Authorization") String token) {
        String username = extractUsernameFromToken(token);
        Comment comment = commentService.createComment(scheduleId, commentDTO, username);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long scheduleId) {
        List<Comment> comments = commentService.getCommentsByScheduleId(scheduleId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        Comment updatedComment = commentService.updateComment(scheduleId, commentId, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(scheduleId, commentId);
        return ResponseEntity.ok().build();
    }

    private String extractUsernameFromToken(String token) {
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }
}
