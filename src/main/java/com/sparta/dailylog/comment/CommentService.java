package com.sparta.dailylog.comment;

import com.sparta.dailylog.comment.dto.CommentRequestDto;
import com.sparta.dailylog.comment.dto.CommentResponseDto;
import com.sparta.dailylog.post.Post;
import com.sparta.dailylog.post.PostRepository;
import com.sparta.dailylog.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        Comment comment = new Comment(commentRequestDto,user,post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
