package com.ykalayy.criyptography.encryption.asymmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RSATest {

    private RSA rsa;
    private final SecureRandom secureRandom = new SecureRandom();

    @Test
    void testEncryption() throws NoSuchAlgorithmException {

        rsa = new RSA(BigInteger.probablePrime(1024, secureRandom), BigInteger.probablePrime(1024, secureRandom));
        String secretMessage = "Secret-Message";
        String cipherText = rsa.encrypt(secretMessage, null);

        String decryptedMessage = rsa.deCrypt(cipherText, null);

        Assertions.assertNotEquals(secretMessage, cipherText, "SecretMessage and CipherText cannot be same");
        Assertions.assertEquals(secretMessage, decryptedMessage, "SecretMessage and decryptedMessage should be same");
    }
}
