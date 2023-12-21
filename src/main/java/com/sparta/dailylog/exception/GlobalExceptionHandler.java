package com.sparta.dailylog.exception;

import com.sparta.dailylog.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //IllegalArgumentExcepition 예외처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponseDto> IllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    //기본 예외처리
    public ResponseEntity<CommonResponseDto> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponseDto("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
