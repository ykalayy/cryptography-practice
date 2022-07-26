package com.ykalayy.cryptography.encryption.symmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CaesarCipherTest {

    CaesarCipher caesarCipher;

    @Test
    void testEncryption() {
        int shiftCount = 5;
        caesarCipher = new CaesarCipher();
        String secretMessage = "Secret-Message";
        String cipherText = caesarCipher.encrypt(secretMessage, shiftCount);

        String decryptedMessage = caesarCipher.deCrypt(cipherText, shiftCount);

        Assertions.assertNotEquals(secretMessage, cipherText, "SecretMessage and CipherText cannot be same");
        Assertions.assertEquals(secretMessage, decryptedMessage, "SecretMessage and decryptedMessage should be same");
    }


    @Test
    void testBruteForce() {

        int shiftCount = 5;
        caesarCipher = new CaesarCipher();
        String secretMessage = "Secret-Message";

        String cipherText = caesarCipher.encrypt(secretMessage, shiftCount);
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            StringBuilder tempPlainText = new StringBuilder();
            for(int k = 0; k < cipherText.length(); k++) {
                tempPlainText.append((char)(cipherText.charAt(k) - shiftCount));
            }
            if(tempPlainText.toString().equals(secretMessage)) {
                return;
            }
        }
        Assertions.fail("Crack with brute-force is failed");

    }
}
