package com.wearedevs.common.objectmapper.securityuser;

import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.dto.SecurityUserDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-08-21"
        //comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class SecurityUserObjectMapperImpl implements SecurityUserObjectMapper {
    @Override
    public SecurityUserDto toDto(CshUser cshUser) {
        return convertEntityToDto(cshUser);
    }

    @Override
    public CshUser toEntity(SecurityUserDto securityUserDto) {
        return null;
    }

    @Override
    public void updateFromDto(SecurityUserDto dto, CshUser entity) {

    }

    private SecurityUserDto convertEntityToDto(CshUser cshUser) {
        return SecurityUserDto.builder()
                .username(cshUser.getLoginId())
                //.password()
                //.accountNonExpired()
                //.accountNonLocked()
                //.credentialsNonExpired()
                //.enabled()
                .userId(cshUser.getId())
                .loginId(cshUser.getLoginId())
                .nickname(cshUser.getNickname())
                .name(cshUser.getName())
                .email(cshUser.getEmail())
                .phoneNumber(cshUser.getPhoneNumber())
                .profileImagePath(cshUser.getProfileImagePath())
                .profileThumbnailImagePath(cshUser.getProfileThumbnailImagePath())
                .gender(cshUser.getGender())
                .birthday(cshUser.getBirthday())
                /*.userDetail(cshUser.getUserDetail())
                .userRoleList(cshUser.getUserRoleList())
                .userAccountInfo(cshUser.getUserAccountInfo())*/
                .build();
    }

}
