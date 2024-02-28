package com.sparta.dailylog.post.controller;


import com.sparta.dailylog.global.CommonResponseDto;
import com.sparta.dailylog.post.repository.PostRepository;
import com.sparta.dailylog.post.service.PostService;
import com.sparta.dailylog.post.dto.PostRequestDto;
import com.sparta.dailylog.post.dto.PostResponseDto;
import com.sparta.dailylog.user.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;


    //게시글 생성
    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto postResponseDto = postService.createPost(postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(postResponseDto);
    }

    //개별 게시글+ 댓글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getPost(postId));
    }

    //전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> postResponseDtoList = postService.getAllPosts();
        return ResponseEntity.ok().body(postResponseDtoList);
    }

    //게시글 수정
    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, requestDto, userDetails.getUser());
    }


    //게시글 삭제
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
  /*      try {
            postService.deletePost(postId, userDetails.getUser());
            String redirectUrl = "api/posts";
            return ResponseEntity.ok().body(new CommonResponseDto("Success", HttpStatus.OK.value()));
            //왜 리다이렉트 안되지
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }*/
        postService.deletePost(postId, userDetails.getUser());
    }

}
