package com.ykalayy.criyptography.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GCDTest {


    @Test
    void testGCD() {
        int result = GCD.gcd(24,9);
        Assertions.assertEquals(3, result, "GCD Result should be 3");
    }
}
