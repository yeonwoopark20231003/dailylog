package com.sparta.dailylog.post;

import com.sparta.dailylog.Timestamped;
import com.sparta.dailylog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table (name = "posts")
public class Post extends Timestamped {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
