package com.sparta.dailylog.post;


import com.sparta.dailylog.CommonResponseDto;
import com.sparta.dailylog.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost (@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto postResponseDto = postService.createPost(postRequestDto,userDetails.getUser());
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

    //전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        List<PostResponseDto> postResponseDtoList= postService.getAllPosts();
        return ResponseEntity.ok().body(postResponseDtoList) ;
    }

    //게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            PostResponseDto postResponseDto = postService.updatePost(postId, requestDto, userDetails.getUser());
            return  ResponseEntity.ok().body(postResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            postService.deletePost(postId, userDetails.getUser());
            String redirectUrl = "api/posts";
            return ResponseEntity.ok().body(new CommonResponseDto("Success", HttpStatus.OK.value()));
            //왜 리다이렉트 안되지
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

}
