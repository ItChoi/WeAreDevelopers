package com.wearedevs.api.login;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;
import com.wearedevs.web.login.service.LoginService;
import com.wearedevs.web.oauth.dto.OAuth2LoginRequestDto;
import com.wearedevs.web.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;
    private final CustomOAuth2UserService customOAuth2UserService;



    @PostMapping("/api/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody LoginRequestDto requestDto, BindingResult result,
                                         HttpServletRequest req, HttpServletResponse res) throws Exception {
        // TODO: result 처리하기.
        boolean isLogin = false;
        try {
            // Jwt || id, pw 여부 판단 필요 여부 체크하기
            loginService.changeLoginApproachKinds(requestDto, req);
            isLogin = loginService.loginProcess(requestDto, req, res);
        } catch (Exception e) {
            log.error("ERROR: {}", e);
        }

        return new ResponseEntity<>(isLogin, HttpStatus.OK);
    }

    @GetMapping("/api/login/oauth2/code/{service}")
    public ResponseEntity<?> oauth2Login(@PathVariable String service, @RequestBody OAuth2LoginRequestDto requestDto) {
        boolean isLogin = false;
        try {
            isLogin = customOAuth2UserService.loginProcess(requestDto);
        } catch (Exception e) {
            log.error("ERROR: {}", e);
        }
        return new ResponseEntity<>(isLogin, HttpStatus.OK);
    }
}
