package com.wearedevs.api.resource.service;

import com.wearedevs.api.resource.domain.Resources;
import com.wearedevs.api.resource.repository.ResourcesRepository;
import com.wearedevs.api.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ResourceService {
    private final ResourcesRepository resourceRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> findResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        //List<RoleResource> resourceList = resourceRepository.findAllResources();
        List<Resources> resourceList = resourceRepository.findAllByResourceTypeOrderByOrderNumDesc("url"); // TODO::: resourceType 정보 필요, enum type 변경
        resourceList.forEach(resource -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            resource.getRoleResourceList().forEach(roleResource -> {
                Role role = roleResource.getRole();
                if (role != null) configAttributes.add(new SecurityConfig(role.getRoleName()));
                result.put(new AntPathRequestMatcher(resource.getResourceName()), configAttributes);
            });

        });

        return result;
    }

}
