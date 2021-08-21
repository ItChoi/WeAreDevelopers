package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.user.LoginAccessType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.web.career.dto.UserCareerInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDetailDto {
    private Long id;
    //private UserDto cshUser;
    private String introduce;
    private String areaOne;
    private String areaTwo;
    private String areaThree;
    private String searchAreaPermitScope;
    private UserActiveStatus userActiveStatus;
    private LoginAccessType loginAccessType;
    private LocalDateTime lastLoginDate;
    private String privacyInfoDisplay;
    private UserCareerInfoDto cshUserCareerInfo;
}
