package com.wearedevs.api.resource;

import com.wearedevs.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/resource")
@RestController
public class ResourceApiController {

    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    @RequestMapping("/test")
    public void test() {
        // TODO::: 인가 정보 실시간 반영을 위해 reload 필요 -> 단순 위치 시킴 -> 위치해 둘 곳 고민 필요
        urlFilterInvocationSecurityMetadataSource.reload();
    }
}
