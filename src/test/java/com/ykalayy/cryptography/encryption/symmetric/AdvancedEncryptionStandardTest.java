package com.ykalayy.cryptography.encryption.symmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AdvancedEncryptionStandardTest {

    private AdvancedEncryptionStandard advancedEncryptionStandard;
    private SecureRandom secureRandom = new SecureRandom();


    @Test
    void testEncryption() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeyException {

        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        advancedEncryptionStandard = new AdvancedEncryptionStandard(secretKey, iv);
        String secretMessage = "Secret-Message";
        String cipherText = advancedEncryptionStandard.encrypt(secretMessage, null);

        String decryptedMessage = advancedEncryptionStandard.deCrypt(cipherText, null);

        Assertions.assertNotEquals(secretMessage, cipherText, "SecretMessage and CipherText cannot be same");
        Assertions.assertEquals(secretMessage, decryptedMessage, "SecretMessage and decryptedMessage should be same");
    }
}
