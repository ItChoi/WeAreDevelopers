package com.wearedevs.web.oauth.dto;


import com.wearedevs.common.enumeration.user.LoginAccessType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    protected OAuth2Attributes() {

    }

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        OAuth2Attributes oAuth2Attributes = new OAuth2Attributes();

        if (registrationId.equals(LoginAccessType.GOOGLE.getCode())) {
            oAuth2Attributes = ofGoogle(userNameAttributeName, attributes);
        } else if (registrationId.equals(LoginAccessType.NAVER.getCode())) {

        } else if (registrationId.equals(LoginAccessType.KAKAO.getCode())) {

        } else if (registrationId.equals(LoginAccessType.GITHUB.getCode())) {

        } else if (registrationId.equals(LoginAccessType.FACEBOOK.getCode())) {

        } else if (registrationId.equals(LoginAccessType.INSTAGRAM.getCode())) {

        }

        return oAuth2Attributes;
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attributes.builder()
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("picture"))
                .build();
    }


}
