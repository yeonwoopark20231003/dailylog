package com.sparta.dailylog.post;

import com.sparta.dailylog.CommonResponseDto;
import com.sparta.dailylog.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto extends CommonResponseDto {

    private Long id;
    private String title;
    private String content;
    private User user;
    private LocalDateTime createDate;


    PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser();
        this.createDate = post.getCreateDate();
    }
}
