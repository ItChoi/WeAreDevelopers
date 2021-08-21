package com.wearedevs.web.user.dto;

import com.wearedevs.web.role.dto.UserRoleDto;
import com.wearedevs.web.user.domain.CshUserDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    // CshUser 필드
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImagePath;
    private String profileThumbnailImagePath;
    private String gender;
    private String birthday;

    private UserDetailDto userDetail;
    private List<UserRoleDto> userRoleList = new ArrayList<>();

    // 커스텀 필드
    List<GrantedAuthority> authorities = new ArrayList<>();
}
