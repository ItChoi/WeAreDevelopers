package com.wearedevs.common.util.msg;

public class ExceptionMsgUtil {
    // 사용자 관련
    public static final String MISMATCH_PASSWORD = "비밀번호가 일치하지 않습니다.";
    public static final String NOT_EXISTS_LOGGED_USER = "로그인된 유저가 없습니다.";
    public static final String NOT_EXISTS_ACCOUNT = "존재하지 않는 계정입니다.";
    public static final String INVALID_LOGIN_INFO = "로그인 아이디 또는 비밀번호가 일치하지 않습니다.";

    // 입력 관련
    public static final String INPUT_REQUIRED_INFO = "필수 정보를 입력해주세요.";

    // 인증 토큰 관련
    public static final String WRONG_JWT_TOKEN = "잘못된 JWT 토큰입니다.";
    public static final String EXPIRED_JWT_TOKEN = "만료된 JWT 토큰입니다.";
    public static final String UNSUPPORTED_JWT_TOKEN = "지원되지 않는 JWT 토큰입니다.";
    public static final String INVALID_JWT_TOKEN = "JWT 토큰 형식이 잘못되었습니다.";

    // 인증 방식 관련
    public static final String NOT_AJAX_REQUEST = "AJAX 인증 방식이 아닙니다.";

    // 인증 관련
    public static final String UN_AUTHORIZED = "인증된 사용자가 아닙니다.";

    // 인가 관련
    public static final String FORBIDDEN = "해당 자원 접근 권한이 없습니다.";
}
