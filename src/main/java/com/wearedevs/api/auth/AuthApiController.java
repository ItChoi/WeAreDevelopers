package com.wearedevs.api.auth;

import com.wearedevs.common.dto.jwt.TokenResponseDto;
import com.wearedevs.web.auth.AuthService;
import com.wearedevs.web.login.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthApiController {
    /*private final AuthService authService;

    @PostMapping("/api/authenticate")
    public ResponseEntity<TokenResponseDto> authorize(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String jwt = authService.createAuthJwtToken(loginRequestDto);
        if (StringUtils.hasText(jwt)) {
            HttpHeaders httpHeaders = authService.addJwtTokenOfBearerType(jwt);
            return new ResponseEntity<>(new TokenResponseDto(jwt), httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/

}
