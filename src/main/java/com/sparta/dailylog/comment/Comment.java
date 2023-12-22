package com.sparta.dailylog.comment;

import com.sparta.dailylog.Timestamped;
import com.sparta.dailylog.comment.dto.CommentRequestDto;
import com.sparta.dailylog.post.Post;
import com.sparta.dailylog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto dto, User user, Post post) {
        this.content = dto.getContent();
        this.user = user;
        this.post = post;
    }


    public void updatePost(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
