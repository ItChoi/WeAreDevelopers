package com.wearedevs.config.security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@RequiredArgsConstructor
@Configuration
public class SpringInitializer implements ApplicationRunner {
    // TODO::: DB에서 Role Hierarchy 가져와서 세팅해주기.
    //private final RoleHierarchyService roleHierarchyService;
    private final RoleHierarchyImpl roleHierarchy;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // roleHierarchyService -> 권한 계층 포맷팅된 정보를 가져와서 세팅
        /*String allHierarchy = roleHierarchyService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);*/
        //roleHierarchy.setHierarchy("");
    }
}
