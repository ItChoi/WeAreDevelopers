package com.wearedevs.api.user;

import com.wearedevs.api.user.dto.UserDto;
import com.wearedevs.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
        Long userId = userService.createUser(userDto);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
