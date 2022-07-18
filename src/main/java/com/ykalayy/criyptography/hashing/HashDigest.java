package com.ykalayy.criyptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashDigest {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final MessageDigest MESSAGE_DIGEST;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private HashDigest() {
    }

    public static byte[] hashString(final String input) {
        byte[] inputStringAsByte = input.getBytes();
        return MESSAGE_DIGEST.digest(inputStringAsByte);
    }
}
