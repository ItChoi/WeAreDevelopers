package com.wearedevs.common.dto.session;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.web.user.domain.CshUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private LoginType loginType;

    @Builder
    public SessionUser(CshUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        // TODO 세션 객체 필드 변경하기
        /*this.picture = user.getProfileImageName();
        this.loginType = user.getLoginType();*/
    }
}
