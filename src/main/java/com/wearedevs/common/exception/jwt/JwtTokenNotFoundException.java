package com.wearedevs.common.exception.jwt;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtTokenNotFoundException extends RuntimeException {
    public JwtTokenNotFoundException(String message) {
        super(message);
    }
}
