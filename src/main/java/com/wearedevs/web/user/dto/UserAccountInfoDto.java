package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.account.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserAccountInfoDto {
    private Long id;
    //private UserDto cshUser;
    private String accountUseType;
    private int passwordFailCount;
    private LocalDateTime passwordFailExceedDate;
    private AccountType accountLockStatus;
    private LocalDateTime lastPasswordChangeDate;
    private String tfaStatus;
}
