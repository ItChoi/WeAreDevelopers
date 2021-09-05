package com.wearedevs.web.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wearedevs.common.enumeration.user.LoginAccessType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import com.wearedevs.web.role.dto.UserRoleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserRegisterRequestDto {
    // CSH_USER
    @NotEmpty
    @Size(min = 3, max = 20)
    private String loginId;
    // TODO: 정규식 적용 필요
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Size(min = 3, max = 300)
    private String password;
    private String nickname;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImagePath;
    private String profileThumbnailImagePath;
    private String gender;
    private String birthday;

    // 사용자 상세
    private String introduce;
    private String areaOne;
    private String areaTwo;
    private String areaThree;
    private String searchAreaPermitScope;
    private LoginAccessType loginType;
    private String privacyInfoDisplay;

    private MultipartFile file;

    // 계정 정보

    // 사용자 권한
    private UserRoleDto userRole;


    @Builder
    public UserRegisterRequestDto(String loginId, String password, String name, String email, MultipartFile file, String profileImagePath, String introduce, String phoneNumber, LoginAccessType loginType, UserAuthority authority) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.file = file;
        this.profileImagePath = profileImagePath;
        this.introduce = introduce;
        this.phoneNumber = phoneNumber;
        this.loginType = loginType;
        this.userRole.setAuthority(authority);
    }
}
