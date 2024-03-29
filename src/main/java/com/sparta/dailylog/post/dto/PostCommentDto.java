package com.sparta.dailylog.post.dto;

import com.sparta.dailylog.global.CommonResponseDto;
import com.sparta.dailylog.comment.entity.Comment;
import com.sparta.dailylog.comment.dto.CommentResponseDto;
import com.sparta.dailylog.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostCommentDto extends CommonResponseDto {

    private Long id;
    private Long user;
    private String title;
    private String content;
    private List<CommentResponseDto> commentResponseDtoList;

    public List<CommentResponseDto> commentListToDtoList(Post post) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> commentList;
        commentList = post.getCommentList();
        commentList.forEach(comment -> {
            var commentDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentDto);
        });
        return commentResponseDtoList;
    }


    public PostCommentDto(Post post) {
        this.id = post.getId();
        this.user = post.getUser().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.commentResponseDtoList = commentListToDtoList(post);
    }
}
