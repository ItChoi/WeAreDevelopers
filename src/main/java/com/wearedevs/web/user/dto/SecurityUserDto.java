package com.wearedevs.web.user.dto;


import com.wearedevs.web.role.dto.UserRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Setter
@Getter
@NoArgsConstructor
public class SecurityUserDto implements UserDetails {
    // UserDetails 객체 필드
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    // 스프링 시큐리티 필드
    List<GrantedAuthority> authorities = new ArrayList<>();

    // 자체 필드
    private Long userId;
    private String loginId;
    private String nickname;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImagePath;
    private String profileThumbnailImagePath;
    private String gender;
    private String birthday;

    private UserDetailDto userDetail;
    private UserRoleDto userRoleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
