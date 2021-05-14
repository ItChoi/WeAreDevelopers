package com.wearedevs.api.user;

import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/user")
    public ResponseEntity<Long> createUser(@RequestBody UserRegisterRequestDto requestDto) {
        Long userId = userService.createUser(requestDto);
        return new ResponseEntity<>(null);
    }


}
