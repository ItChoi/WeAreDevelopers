package com.wearedevs;

import com.wearedevs.common.enumeration.user.UserAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ApplicationBasicTest {
    @Test
    void 옵셔널_NULL_테스트1() {
        class Test123 {
            private String a;
            private String b;
            public String getA() {
                return a;
            }
            public String getB() {
                return b;
            }
        }

        //Test123 test = new Test123();
        Test123 test = null;

        assertThrows(NullPointerException.class, () -> {
            Optional.ofNullable(test.getA());
        });

        Optional<Test123> test1 = null;
        assertThrows(NullPointerException.class, () -> {
            test1.get();
        });

        //SecurityContext securityContext = Optional.ofNullable(getSecurityContext());
        assertThrows(NoSuchElementException.class, () -> {
            Optional.ofNullable(test1).get().get();
        });
    }

    @Test
    void 스트링_유틸_테스트() {
        boolean b = StringUtils.hasText(null);
        assertThat(b).isFalse();
    }

    @Test
    void 서드파티_권한_생성_테스트() {
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(UserAuthority.ANONYMOUS.getFullCode());
        assertThat(authorityList).isNotNull();
        assertEquals(1, authorityList.size());

    }
}
