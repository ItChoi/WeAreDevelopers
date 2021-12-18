package com.wearedevs.common.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Getter
public class SessionUtil {

    public static String findSessionAttrByHttpServletRequest(HttpServletRequest request, String attributeName) {
        if (request == null) return "";

        return (String) request.getSession().getAttribute(attributeName);
    }
}
