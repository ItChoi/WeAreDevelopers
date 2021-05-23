package com.wearedevs.web.oauth.service;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.common.enumeration.user.UserAuthority;
import com.wearedevs.web.oauth.dto.OAuth2Attributes;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
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
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        LoginType loginTypeCode = LoginType.convertByCode(registrationId);
        CshUser targetUser = changeUser(attributes, loginTypeCode);

        //httpSession.setAttribute("user", );


        return null;
    }

    @Transactional
    private CshUser changeUser(OAuth2Attributes attributes, LoginType loginType) {
        CshUser findUser = userRepository.findByEmailAndLoginType(attributes.getEmail(), loginType).orElse(null);

        if (findUser != null) {
            CshUser cshUser = userService.builderCshUserByRequestDto(settingUserRegisterRequestDto(attributes, loginType));
            userRepository.save(cshUser);

            return cshUser;
        }

        findUser.setName(attributes.getName());
        findUser.setProfileImageName(attributes.getPicture());

        return findUser;


    }

    private UserRegisterRequestDto settingUserRegisterRequestDto(OAuth2Attributes attributes, LoginType loginType) {
        return UserRegisterRequestDto.builder()
                .name(attributes.getName())
                .email(attributes.getEmail())
                .profileImageName(attributes.getPicture()) // S3 등록 필요
                .loginType(loginType)
                .authority(UserAuthority.USER)
                .build();
    }
}