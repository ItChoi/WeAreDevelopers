package com.wearedevs.api.login;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;
import com.wearedevs.web.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto requestDto, BindingResult result,
                                                  HttpServletRequest req, HttpServletResponse res) throws Exception {
        // TODO: result 처리하기.
        boolean isLogin = false;
        try {
            loginService.changeLoginApproachKinds(requestDto, req);
            isLogin = loginService.loginProcess(requestDto, req, res);
        } catch (Exception e) {
            log.error("ERROR: {}", e);
        }

        return new ResponseEntity<>(isLogin, HttpStatus.OK);
    }


    @GetMapping("/api/login/oauth2/code/{service}")
    public ResponseEntity<?> oauth2Login(@PathVariable String service) {


        return null;
    }


}
