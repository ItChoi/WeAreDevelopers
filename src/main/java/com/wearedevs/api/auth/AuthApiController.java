package com.wearedevs.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

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
