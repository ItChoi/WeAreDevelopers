package com.wearedevs.common.dto.session;

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

    @Builder
    public SessionUser(CshUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getProfileImageName();
    }
}
