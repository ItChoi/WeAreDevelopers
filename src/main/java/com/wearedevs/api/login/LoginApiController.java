package com.wearedevs.api.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    @GetMapping("/api/login")
    public void asdasd() {

    }


    @GetMapping("/api/login/oauth2/code/{service}")
    public ResponseEntity<?> oauth2Login(@PathVariable String service) {


        return null;
    }


}
