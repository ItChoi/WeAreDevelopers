package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.web.user.domain.CshUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserDetailDto {
    private Long id;
    private CshUser cshUser;
    private String introduce;
    private String areaOne;
    private String areaTwo;
    private String areaThree;
    private String searchAreaPermitScope;
    private UserActiveStatus userActiveStatus;
    private LoginType loginType;
    private LocalDateTime lastLoginDate;
    private String privacyInfoDisplay;
}
