package com.sparta.dailylog.post;

import com.sparta.dailylog.post.dto.PostCommentDto;
import com.sparta.dailylog.post.dto.PostRequestDto;
import com.sparta.dailylog.post.dto.PostResponseDto;
import com.sparta.dailylog.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto);
        post.setUser(user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public PostCommentDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return new PostCommentDto(post);
    }


    public List<PostResponseDto> getAllPosts() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> list = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        list.forEach(post -> {
            var postDto = new PostResponseDto(post);
            postResponseDtoList.add(postDto);
        });
        return postResponseDtoList;
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findpost(postId);
        if (!user.getId().equals(post.getUser().getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        post.updatePost(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public void deletePost(Long postId, User user) {
        Post post = findpost(postId);
        if (!user.getId().equals(post.getUser().getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        postRepository.delete(post);
    }


    public Post findpost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return post;
    }


}
