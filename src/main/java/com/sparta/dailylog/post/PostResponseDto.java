package com.sparta.dailylog.post;

import com.sparta.dailylog.CommonResponseDto;
import lombok.*;

@Getter
@Setter
public class PostResponseDto extends CommonResponseDto {

    private Long id;
    private String title;
    private String content;
    private String user;

    PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser().getUserId();
    }
}
