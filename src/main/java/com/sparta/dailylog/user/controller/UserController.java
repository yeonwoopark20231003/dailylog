package com.sparta.dailylog.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.dailylog.global.CommonResponseDto;
import com.sparta.dailylog.jwt.JwtUtil;
import com.sparta.dailylog.user.dto.UserRequestDto;
import com.sparta.dailylog.user.service.KakaoService;
import com.sparta.dailylog.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtUtil jwtUtil;
  private final KakaoService kakaoService;

  @PostMapping("/signup")
  public ResponseEntity<CommonResponseDto> signup(
      @Valid @RequestBody UserRequestDto userRequestDto) {
    try {
      userService.signup(userRequestDto);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
          .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    return ResponseEntity.status(HttpStatus.CREATED.value())
        .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponseDto> login(@RequestBody UserRequestDto userRequestDto,
      HttpServletResponse response) {
    try {
      userService.login(userRequestDto, response);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
          .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    response.setHeader(JwtUtil.AUTHORIZATION_HEADER,
        jwtUtil.createToken(userRequestDto.getUserId()));

    return ResponseEntity.status(HttpStatus.OK.value())
        .body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
  }

  @GetMapping("/user/kakao/callback")
  public String kakaoLogin(@RequestParam String code, HttpServletResponse response)
      throws JsonProcessingException {
    String token = kakaoService.kakaoLogin(code);

    Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
    cookie.setPath("/");
    response.addCookie(cookie);

    return "redirect:/";
  }

}
