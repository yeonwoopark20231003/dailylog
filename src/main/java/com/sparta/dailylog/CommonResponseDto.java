package com.sparta.dailylog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.dailylog.comment.dto.CommentRequestDto;
import com.sparta.dailylog.comment.dto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude (JsonInclude.Include.NON_NULL)
public class CommonResponseDto {
    private String msg;
    private Integer statusCode;

}
