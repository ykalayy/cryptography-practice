package com.ykalayy.cryptography.encryption.symmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OneTimePadTest {


    OneTimePad oneTimePad;

    @Test
    void testEncryption() {
        oneTimePad = new OneTimePad();
        String secretMessage = "Secret-Message";
        int[] privateKeys = OneTimePad.RandomGenerator.generateRnd(secretMessage.length());
        String cipherText = oneTimePad.encrypt(secretMessage, privateKeys);

        String decryptedMessage = oneTimePad.deCrypt(cipherText, privateKeys);

        Assertions.assertNotEquals(secretMessage, cipherText, "SecretMessage and CipherText cannot be same");
        Assertions.assertEquals(secretMessage, decryptedMessage, "SecretMessage and decryptedMessage should be same");
    }
}
