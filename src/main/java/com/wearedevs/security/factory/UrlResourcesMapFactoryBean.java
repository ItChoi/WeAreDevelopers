package com.wearedevs.security.factory;

import com.wearedevs.api.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
    private final ResourceService resourceService;
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;


    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if (resourceMap == null) {
            init();
        }
        return resourceMap;
    }

    private void init() {
        resourceMap = resourceService.findResourceList();

    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    /*public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }*/


}
