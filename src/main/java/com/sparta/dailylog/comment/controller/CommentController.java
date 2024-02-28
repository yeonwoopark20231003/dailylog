package com.sparta.dailylog.comment.controller;

import com.sparta.dailylog.global.CommonResponseDto;
import com.sparta.dailylog.comment.service.CommentService;
import com.sparta.dailylog.comment.dto.CommentRequestDto;
import com.sparta.dailylog.comment.dto.CommentResponseDto;
import com.sparta.dailylog.user.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts/{postId}")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommonResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        try {
            CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUser(), postId);
            return ResponseEntity.ok().body(commentResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponseDto> updateComment(
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        try {
            CommentResponseDto commentResponseDto = commentService.updateComment(requestDto, userDetails.getUser(), postId, commentId);
            return ResponseEntity.ok().body(commentResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentService.delete(userDetails.getUser(), postId, commentId);
    }

}
