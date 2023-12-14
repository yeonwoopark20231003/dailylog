package com.sparta.dailylog.post;


import com.sparta.dailylog.CommonResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CommonResponseDto> postPost (@RequestBody PostRequestDto postRequestDto){

        PostResponseDto postResponseDto = postService.post(postRequestDto);
        return ResponseEntity.ok().body(postResponseDto);
    }


/*
    @GetMapping
    public ResponseEntity<CommonResponseDto> getPost {}*/

}
