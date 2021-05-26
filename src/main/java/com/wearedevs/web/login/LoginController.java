package com.wearedevs.web.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    @GetMapping("/front/user/login")
    public String loginPage() {
        System.out.println("true = " + true);
        return "front/user/login";
    }
}
