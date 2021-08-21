package com.wearedevs.web.oauth.service;

import com.wearedevs.common.dto.session.SessionUser;
import com.wearedevs.common.enumeration.user.LoginAccessType;
import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.web.oauth.dto.OAuth2Attributes;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.repository.UserRepository;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

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

        /* TODO 임시 주석
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(targetUser.getCshUserRole().getAuthority().getCode())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );*/
        return null;
    }

    @Transactional
    public CshUser changeUser(OAuth2Attributes attributes, LoginAccessType loginAccessType) {
        /* 임시 주석
        CshUser findUser = userRepository.findByEmailAndLoginType(attributes.getEmail(), loginAccessType).orElse(null);
        if (findUser == null) {
            CshUser cshUser = userService.builderCshUserByRequestDto(settingUserRegisterRequestDto(attributes, loginAccessType));
            userRepository.save(cshUser);

            return cshUser;
        }

        findUser.setName(attributes.getName());
        findUser.setProfileImageName(attributes.getPicture()); 임시 주석

        return findUser;*/
        return null;
    }

    /*private UserRegisterRequestDto settingUserRegisterRequestDto(OAuth2Attributes attributes, LoginAccessType loginType) {
        return UserRegisterRequestDto.builder()
                .name(attributes.getName())
                .email(attributes.getEmail())
                .profileImageName(attributes.getPicture()) // S3 등록 필요
                .loginType(loginType)
                .authority(UserAuthority.USER)
                .build();
    }*/
}
