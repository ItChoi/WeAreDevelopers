package com.wearedevs.web.user.dto;


import com.wearedevs.common.enumeration.user.UserAuthority;
import com.wearedevs.common.utils.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
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
    // CshUser.java
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

    // CshUserDetail 필드
    /*private Long userDetailId;
    private String*/

    // CshUserRole 필드

    // CshUserAccountInfo 필드


    /*private UserDetailDto userDetail;
    private UserRoleDto userRoleList;
    private UserAccountInfoDto userAccountInfo;*/

    public void setId(Long userId) {
        this.userId = userId;
    }

    public void setLoginId(String username) {
        this.loginId = username;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication currentUser = SecurityUtil.getCurrentUser();
        // 익명 사용자도 익명 사용자 객체가 존재해야 정상이지만, 우선 Auth가 null이면 익명 사용자 권한 반환
        if (currentUser == null) return AuthorityUtils.createAuthorityList(UserAuthority.ANONYMOUS.getFullCode());
        return currentUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return StringUtils.hasText(username) ? username : loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 향후 개발
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        //if (userAccountInfo == null || AccountType.LOCK == userAccountInfo.getAccountLockStatus()) return false;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (SecurityUtil.getCurrentUser() == null) return false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        /*if (userAccountInfo == null) return false;
        String accountUseType = userAccountInfo.getAccountUseType();
        if (StringUtils.hasText(accountUseType) && TwoAnswerType.Y.getStrShortCode().equals(accountUseType)) {
            return true;
        }*/

        return false;
    }

    @Builder
    public SecurityUserDto(String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<GrantedAuthority> authorities, Long userId, String loginId, String nickname, String name, String email, String phoneNumber, String profileImagePath, String profileThumbnailImagePath, String gender, String birthday) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
        this.userId = userId;
        this.loginId = loginId;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImagePath = profileImagePath;
        this.profileThumbnailImagePath = profileThumbnailImagePath;
        this.gender = gender;
        this.birthday = birthday;
    }
}
