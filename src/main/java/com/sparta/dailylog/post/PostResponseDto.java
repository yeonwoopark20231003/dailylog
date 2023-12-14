package com.sparta.dailylog.post;

import com.sparta.dailylog.CommonResponseDto;
import lombok.*;

@Getter
@Setter
public class PostResponseDto extends CommonResponseDto {

    private Long id;
    private String title;
    private String content;

    PostResponseDto(Post post) {
        Long id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
