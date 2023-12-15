package com.sparta.dailylog.post;


import com.sparta.dailylog.CommonResponseDto;
import com.sparta.dailylog.user.UserDetailsImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost (@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto postResponseDto = postService.createPost(postRequestDto);
        return ResponseEntity.ok().body(postResponseDto);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> getPost(@PathVariable Long postId){

        try {
            PostResponseDto postResponseDto = postService.getPost(postId);
            return ResponseEntity.ok().body(postResponseDto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }


    }

}
