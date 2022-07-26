package com.ykalayy.cryptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static final String HASH_ALGORITHM = "MD5";
    private static final MessageDigest MESSAGE_DIGEST;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String digest(String message) {
        return new String(MESSAGE_DIGEST.digest(message.getBytes()));
    }
}
