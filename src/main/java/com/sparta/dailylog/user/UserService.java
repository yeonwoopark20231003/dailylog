package com.sparta.dailylog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if (userRepository.findByUserId(userId).isPresent()){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(userId,password);
        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto) {
        String userId = userRequestDto.getUserId();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("등록되지 않은 사용자입니다."));

        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
