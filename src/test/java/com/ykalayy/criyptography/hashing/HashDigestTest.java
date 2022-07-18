package com.ykalayy.criyptography.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class HashDigestTest {

    @Test
    void hashStringTestWithSameInput() {
        String toHash = "INPUT_TEST_STRING";
        byte[] hashResult_1 = HashDigest.hashString(toHash);
        byte[] hashResult_2 = HashDigest.hashString(toHash);
        Assertions.assertArrayEquals(hashResult_1, hashResult_2, "Same input hashs should be same");
    }

    @Test
    void hashStringTestWithDifferentInput() {
        String toHash1 = "INPUT_TEST_STRING_1";
        String toHash2 = "INPUT_TEST_STRING_2";
        byte[] hashResult_1 = HashDigest.hashString(toHash1);
        byte[] hashResult_2 = HashDigest.hashString(toHash2);
        Assertions.assertFalse(Arrays.equals(hashResult_1, hashResult_2), "Two different input hash result are should not be same");
    }
}
