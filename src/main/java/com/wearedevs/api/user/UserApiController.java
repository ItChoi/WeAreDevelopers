package com.wearedevs.api.user;

import com.wearedevs.common.utils.LoggingUtils;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                LoggingUtils.validBindingResult(bindingResult);
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            log.error("ERROR: {}", e);
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(userId, httpStatus);
    }




}
