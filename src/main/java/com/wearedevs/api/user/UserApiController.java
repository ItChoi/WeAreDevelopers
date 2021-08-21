package com.wearedevs.api.user;

import com.wearedevs.common.utils.LoggingUtil;
import com.wearedevs.web.user.dto.UserDetailInfoResponseDto;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/user")
    public ResponseEntity<Long> createUser(@Valid @RequestBody UserRegisterRequestDto requestDto, BindingResult bindingResult) {
        Long userId = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (bindingResult.hasErrors() || (userId = userService.createUser(requestDto)) == null) {
                LoggingUtil.validBindingResult(bindingResult);
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            log.error("ERROR: {}", e);
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(userId, httpStatus);
    }

    @GetMapping(value = "/api/user/{userId}")
    public ResponseEntity<UserDetailInfoResponseDto> userDetail(@PathVariable Long userId) {
        UserDetailInfoResponseDto responseDto = null;
        try {
            responseDto = userService.findUserDetailInfo(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }





}
