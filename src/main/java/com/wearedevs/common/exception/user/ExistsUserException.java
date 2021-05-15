package com.wearedevs.common.exception.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExistsUserException extends RuntimeException {

    public ExistsUserException(String message) {
        super(message);
    }

}
