package com.wearedevs.web.user.domain;

import com.wearedevs.api.user.domain.CshUser;
import com.wearedevs.common.util.RegexpUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CshUserTest {
    private CshUser user;
    /**
     * 1. 아이디 검증
     *   - 길이: 3 ~ 20
     *   - 영문 + 숫자 조합
     *   - 로그인 아이디 & 이메일로 로그인 가능하다.
     * 2. 비밀번호 검증
     *   - 길이: 8 ~ 16
     *   - 영문 + 특수문자 조합
     *   - 비밀번호 변경시 기존 비밀번호 사용 X
     * 3. 이메일 검증
     *   - (영문 + 숫자 + 특수문자) + @ + 영문.영문.영문 (1, 2, 3 가능)
     * 4. 닉네임 검증
     *   - 길이: 1 ~ 8
     *   - 영문, 숫자, 한글, 특수문자 가능
     * 5. 폰 번호 검증
     *   - 010-0000-0000 가능 01000000000 가능
     */

    @ParameterizedTest
    @ValueSource(strings = {"test", "tes", "te", "test", "test", "ttee"})
    void 사용자_로그인_아이디_검증_성공(String loginId) {
        assertTrue(RegexpUtil.isOnlyEnglish(loginId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test짱", "test★", "tteeð"})
    void 사용자_로그인_아이디_검증_실패(String loginId) {
        assertThatThrownBy(() -> {
            if (!RegexpUtil.isOnlyEnglish(loginId)) throw new NotAvailableLoginIdException(("형식이 틀립니다."));
        })
                .isInstanceOf(NotAvailableLoginIdException.class)
                .hasMessageContaining("형식이 틀립니다.");

        assertThatThrownBy(() -> {
            assertThat(loginId.length()).isGreaterThan(3).isLessThan(12);
        })
                .isInstanceOf(NotAvailableLoginIdException.class)
                .hasMessageContaining("형식이 틀립니다");
    }
}