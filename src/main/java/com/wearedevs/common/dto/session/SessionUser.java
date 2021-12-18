package com.wearedevs.common.dto.session;

import com.wearedevs.common.enumeration.user.LoginAccessType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private LoginAccessType loginAccessType;

    /*@Builder
    public SessionUser(CshUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getProfileImagePath();
        this.loginAccessType = user.getLoginType();
    }*/
}
