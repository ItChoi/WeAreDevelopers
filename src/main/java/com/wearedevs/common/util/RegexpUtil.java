package com.wearedevs.common.util;

import java.util.regex.Pattern;

// 정규표현식 유틸리티
public class RegexpUtil {

    public static boolean isOnlyEnglish(String value) {
        return isAvailableValue("^[a-zA-Z]*", value);
    }

    public static boolean isIncludeEnglishOrDigest(String value) {
        return isAvailableValue("^[a-zA-Z]*", value);
    }

    private static boolean isAvailableValue(String pattern, String value) {
        return Pattern.matches(pattern, value);
    }
}
