package com.wearedevs.web.user.dto;


import com.wearedevs.web.role.dto.UserRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
    List<GrantedAuthority> authorities;

    // 자체 필드
    private Long userId;
    private String nicmname;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImagePath;
    private String profileThumbnailImagePath;
    private String gender;
    private String birthday;
    private UserRoleDto cshUserRole;


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
