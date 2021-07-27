package com.wearedevs;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

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


}
