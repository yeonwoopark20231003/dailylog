package com.sparta.dailylog.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto post(PostRequestDto postRequestDto){
        Post post = new Post(postRequestDto.getTitle(),postRequestDto.getContent());
        postRepository.save(post);
        return new PostResponseDto(post);
    }

}
