package com.wearedevs.web.login.service;

import com.wearedevs.common.exception.user.UserNotFoundException;
import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;
import com.wearedevs.web.user.repository.UserRepository;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
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
    private final UserService userService;
    private final AuthenticationProvider provider;
    private final UserRepository userRepository;
    SessionAuthenticationStrategy sessionStrategy;

    private boolean isNotEmptyUsernameOrPassword(String username, String password) {
        return StringUtils.hasText(username) && StringUtils.hasText(password);
    }


    @Override
    public LoginResponseDto loginProcess(LoginRequestDto requestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        String username = requestDto.getUsername();
        username = username.trim();
        String inputPassword = requestDto.getPassword();
        if (isNotEmptyUsernameOrPassword(username, inputPassword)) throw new UserNotFoundException("계정 정보를 정확히 입력해주세요.");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, inputPassword);
        // setDetails -> ip & cache 세팅
        Authentication authenticate = provider.authenticate(authToken);

        sessionStrategy.onAuthentication(authenticate, req, res);
        /*if (userService.existsUserByUsername(username)) {

        }*/

        //if (isAvailableUsernameAndPassword(username, inputPassword)) throw new UserNotFoundException("비밀번호를 정확히 입력해주세요.");
        /**
         * 계정 정보 검증
         * 1. username 빈 값 체크 & 존재하는지 확인
         * 2. 비밀번호 빈값 체크 & 계정 확인 (아이디 비밀번호 일치)
         *
         */


        // TODO: Error Handler 만들기
        //UserDetails userDetails = null; // cache 있으면 가져오기 (?)
        try {
            //userDetails = userService.loadUserByUsername(username);
            // 계정 자체 인증
            //userService.beforeLoginUserAuthChecksByUsername(username);
            //

        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }


        /*UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, inputPassword, userDetails.getAuthorities());
        Authentication authenticate = provider.authenticate(upat);
        SecurityContextHolder.getContext().setAuthentication(authenticate);*/



        // 인가
        return null;
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
