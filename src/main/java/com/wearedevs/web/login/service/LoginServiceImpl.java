package com.wearedevs.web.login.service;

import com.wearedevs.common.exception.user.UserNotFoundException;
import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginServiceImpl implements LoginService {
    //private final UserService userService;
    private final AuthenticationProvider authProvider;
    //private final UserRepository userRepository;
    private final SessionAuthenticationStrategy sessionStrategy;

    private boolean isNotEmptyUsernameOrPassword(String username, String password) {
        return !StringUtils.hasText(username) || !StringUtils.hasText(password);
    }


    @Override
    public boolean loginProcess(LoginRequestDto requestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        String username = requestDto.getUsername();
        username = username.trim();
        String inputPassword = requestDto.getPassword();
        if (isNotEmptyUsernameOrPassword(username, inputPassword)) throw new UserNotFoundException("계정 정보를 정확히 입력해주세요.");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, inputPassword);
        /**
         * setDetails -> ip & cache 세팅
         * session -> 스프링 시큐리티는 프로세스 안에서 세션을 등록하여 사용하는데,
         * 예를 들어 로그인만 하고 TFA 하지 않고 끈 경우, 로그인 페이지 왔을 때 세션 여부를 확인 후 TFA를 띄워주는 등의 동작이 필요
         */

        Authentication authenticate = authProvider.authenticate(authToken);

        String remoteAddr = req.getHeader("X-FORWARDED-FOR");
        if (!StringUtils.hasText(remoteAddr)) remoteAddr = req.getRemoteAddr();




        /**
         * TODO List: 인증 성공 이후
         * 0. 비밀번호 관련 값 Null로 세팅 - eraseCredentials()
         * 1. 세션 등록
         * 2. Security Context Auth 객체 등록
         * 3. 리멤버미
         * 4. Success Handler 호출 (필요가 있으려나?)
         */


        // 로그인 성공 -> TFA 이전 상태에서 세션과 Security Context 중 어떤 거로 찾을 지 테스트하기.
        sessionStrategy.onAuthentication(authenticate, req, res);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 인가
        return authenticate.isAuthenticated();
    }

    private boolean isAvailablePassword(String inputPassword) {
        // TODO: RegExUtil - 비밀번호 정규식 적용
        return true;
    }

    @Override
    public void changeLoginApproachKinds(LoginRequestDto requestDto, HttpServletRequest request) {
        // TODO: request를 통해 UsernamePwAuth || Jwt인지 체크후 세팅해주기.
        requestDto.setLoginApproachKinds(null);
    }
}
