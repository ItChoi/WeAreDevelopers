package com.wearedevs.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Slf4j
public class LoggingUtils {

    public static void validBindingResult(BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            log.error("ERROR: {}", error);
        }
    }
}
