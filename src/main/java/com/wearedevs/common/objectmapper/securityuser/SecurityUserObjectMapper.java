package com.wearedevs.common.objectmapper.securityuser;

import com.wearedevs.common.objectmapper.GenericMapper;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.dto.SecurityUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityUserObjectMapper extends GenericMapper<SecurityUserDto, CshUser> {

}
