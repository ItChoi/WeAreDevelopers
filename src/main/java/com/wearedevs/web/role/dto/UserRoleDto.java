package com.wearedevs.web.role.dto;


import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRoleDto {
    private Long id;
    private UserAuthority authority;
}
