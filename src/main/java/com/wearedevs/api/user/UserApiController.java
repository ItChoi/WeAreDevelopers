package com.wearedevs.api.user;

import com.wearedevs.api.user.dto.UserDto;
import com.wearedevs.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Void> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
        Long userId = userService.createUser(userDto);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
