package com.wearedevs.web.oauth.service;

import com.wearedevs.common.dto.session.SessionUser;
import com.wearedevs.common.enumeration.user.LoginAccessType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.web.oauth.dto.OAuth2Attributes;
import com.wearedevs.web.oauth.dto.OAuth2LoginRequestDto;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenProvider tokenProvider;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        LoginAccessType loginAccessTypeCode = LoginAccessType.convertByCode(registrationId);
        CshUser targetUser = changeUser(attributes, loginAccessTypeCode);

        httpSession.setAttribute("user", SessionUser.builder().user(targetUser).build());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(targetUser.getUserRole().getAuthority().getCode())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    @Transactional
    public CshUser changeUser(OAuth2Attributes attributes, LoginAccessType loginAccessType) {
        CshUser findUser = userRepository.findByEmailAndLoginType(attributes.getEmail(), loginAccessType).orElse(null);
        if (findUser == null) {
            CshUser cshUser = userService.builderCshUserByRequestDto(settingUserRegisterRequestDto(attributes, loginAccessType));
            userRepository.save(cshUser);

            return cshUser;
        }

        findUser.setName(attributes.getName());
        findUser.setProfileImagePath(attributes.getPicture());

        return findUser;
    }

    /**
     * OAuth2 로그인 프로세스 흐름
     * 1. 소셜 서비스 이용 요청
     * 2. 해당 소셜 서비스 권한 동의 유도
     *  - 해당 소셜 서비스에 대한 동의 수락 값 보관 필요
     *      2. JWT 안에 정보 포함
     *      3. 세션
     *  3.
     */
    public boolean loginProcess(OAuth2LoginRequestDto requestDto) {
        // TODO

        return false;
    }

    private UserRegisterRequestDto settingUserRegisterRequestDto(OAuth2Attributes attributes, LoginAccessType loginType) {
        return UserRegisterRequestDto.builder()
                .name(attributes.getName())
                .email(attributes.getEmail())
                .profileImagePath(attributes.getPicture()) // S3 등록 필요
                .loginType(loginType)
                .authority(UserAuthority.USER)
                .build();
    }
}
