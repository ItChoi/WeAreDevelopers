package com.wearedevs.api.user.dto;

import com.wearedevs.api.user.domain.CshUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserSecurity extends User {
    private final CshUser user;

    public UserSecurity(CshUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLoginId(), user.getPassword(), authorities);
        this.user = user;
    }
}
