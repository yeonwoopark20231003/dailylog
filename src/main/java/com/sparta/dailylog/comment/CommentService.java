package com.sparta.dailylog.comment;

import com.sparta.dailylog.comment.dto.CommentRequestDto;
import com.sparta.dailylog.comment.dto.CommentResponseDto;
import com.sparta.dailylog.post.Post;
import com.sparta.dailylog.post.PostRepository;
import com.sparta.dailylog.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    //댓글 작성
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        Comment comment = new Comment(commentRequestDto,user,post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, User user, Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 댓글입니다. 입니다."));
        if (!user.getUserId().equals(comment.getUser().getUserId())){
            throw new RejectedExecutionException("댓글 작성자만 수정할 수 있습니다.");
        }
        comment.updatePost(requestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);

    }

    public void delete(User user,Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 댓글입니다. 입니다."));
        if (!user.getUserId().equals(comment.getUser().getUserId())){
            throw new RejectedExecutionException("댓글 작성자만 수정할 수 있습니다.");
        }
        commentRepository.delete(comment);
        return;
    }
}
