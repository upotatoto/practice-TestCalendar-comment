package com.sparta.springcommentcalendar.controller;

import com.sparta.springcommentcalendar.dto.UserDTO;
import com.sparta.springcommentcalendar.entity.User;
import com.sparta.springcommentcalendar.security.JwtTokenProvider;
import com.sparta.springcommentcalendar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        User user = new User();  // UserDTO를 User 엔티티로 변환
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // 비밀번호를 암호화
        user.setRole("USER");  // 기본 역할 설정

        userService.registerUser(user);  // 변환된 User 객체를 서비스에 전달
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User user = userService.authenticate(userDTO.getUsername(), userDTO.getPassword(), passwordEncoder);
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
}
