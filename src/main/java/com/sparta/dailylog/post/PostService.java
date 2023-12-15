package com.sparta.dailylog.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto){
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다.") );
        return new PostResponseDto(post);
    }


    public List<PostResponseDto> getAllPosts() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> list = postRepository.findAll();
        list.forEach(post -> {
            var postDto = new PostResponseDto(post);
            postResponseDtoList.add(postDto);
        });
        return postResponseDtoList;
    }
}
