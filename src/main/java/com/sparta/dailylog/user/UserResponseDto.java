package com.sparta.dailylog.user;

public class UserResponseDto {
    private Long id;
    private String userId;
    private String password;

    public UserResponseDto(String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}
