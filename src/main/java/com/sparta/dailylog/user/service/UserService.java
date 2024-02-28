package com.sparta.dailylog.user.service;

import com.sparta.dailylog.global.CommonResponseDto;
import com.sparta.dailylog.jwt.JwtUtil;
import com.sparta.dailylog.jwt.Token;
import com.sparta.dailylog.jwt.TokenRepository;
import com.sparta.dailylog.user.entity.User;
import com.sparta.dailylog.user.dto.UserRequestDto;
import com.sparta.dailylog.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    public void signup(UserRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if (userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(userId, password);
        userRepository.save(user);
    }

    public ResponseEntity<CommonResponseDto> login(UserRequestDto userRequestDto, HttpServletResponse response) {
        String userId = userRequestDto.getUserId();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }


        // 이미 로그인 되어 있는지 확인
        if (!tokenRepository.findByUser(user).isEmpty()) {
            Token token = tokenRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("존재하는데 존재하지 않는 발생할리 없는 에러"));
            String beareredToken = jwtUtil.BEARER_PREFIX + token.getTokenValue();
            response.setHeader(JwtUtil.AUTHORIZATION_HEADER, beareredToken);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new CommonResponseDto("이미 로그인 되어 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }

        // header에 Role이 담긴 JwtToken탑재
        String bearerToken = jwtUtil.createToken(userRequestDto.getUserId());
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, bearerToken);


        // 토큰을 table에 넣는다.
        String token = bearerToken.substring(7);
        Token tokenObject = new Token(token, user);
        tokenRepository.save(tokenObject);

        return ResponseEntity.status(HttpStatus.OK.value()).body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));

    }

}
