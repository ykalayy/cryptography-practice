package com.ykalayy.criyptography.encryption.symmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DataEncryptionStandardTest {


    private DataEncryptionStandard dataEncryptionStandard;
    private SecureRandom secureRandom = new SecureRandom();


    @Test
    void testEncryption() throws NoSuchAlgorithmException {

        KeyGenerator.getInstance()
        SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
        byte[] iv = new byte[8];
        secureRandom.nextBytes(iv);

        dataEncryptionStandard = new DataEncryptionStandard(secretKey, iv);
        String secretMessage = "Secret-Message";
        String cipherText = dataEncryptionStandard.encrypt(secretMessage, null);

        String decryptedMessage = dataEncryptionStandard.deCrypt(cipherText, null);

        Assertions.assertNotEquals(secretMessage, cipherText, "SecretMessage and CipherText cannot be same");
        Assertions.assertEquals(secretMessage, decryptedMessage, "SecretMessage and decryptedMessage should be same");
    }
}
