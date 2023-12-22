package com.sparta.dailylog.comment.dto;

import com.sparta.dailylog.CommonResponseDto;
import com.sparta.dailylog.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto extends CommonResponseDto {
    private Long commentId;
    private String content;
    private String userId;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
        this.createdAt = comment.getCreatedAt();
    }
}
