package com.wearedevs.api.login;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public void login(LoginRequestDto requestDto, BindingResult result) {
        // TODO: result 처리하기.
        log.info("TEST");



    }


    @GetMapping("/api/login/oauth2/code/{service}")
    public ResponseEntity<?> oauth2Login(@PathVariable String service) {


        return null;
    }


}
