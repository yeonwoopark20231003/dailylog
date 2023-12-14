package com.sparta.dailylog.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Getter
@Entity
@NoArgsConstructor
@Table (name = "posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }
}
