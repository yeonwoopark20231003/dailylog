package com.sparta.dailylog.post.dto;

import com.sparta.dailylog.CommonResponseDto;
import com.sparta.dailylog.post.Post;
import lombok.*;

@Getter
@Setter
public class PostResponseDto extends CommonResponseDto {

    private Long id;
    private String title;
    private String content;
    private String user;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser().getUserId();
    }
}
