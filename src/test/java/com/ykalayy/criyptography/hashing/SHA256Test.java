package com.ykalayy.criyptography.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SHA256Test {

    @Test
    void hashStringTestWithSameInput() {
        String toHash = "INPUT_TEST_STRING";
        String hashResult_1 = SHA256.hashString(toHash);
        String hashResult_2 = SHA256.hashString(toHash);
        Assertions.assertEquals(hashResult_1, hashResult_2, "Same input hashs should be same");
    }

    @Test
    void hashStringTestWithDifferentInput() {
        String toHash1 = "INPUT_TEST_STRING_1";
        String toHash2 = "INPUT_TEST_STRING_2";
        String hashResult_1 = SHA256.hashString(toHash1);
        String hashResult_2 = SHA256.hashString(toHash2);
        Assertions.assertNotEquals(hashResult_1, hashResult_2, "Two different input hash result are should not be same");
    }
}
