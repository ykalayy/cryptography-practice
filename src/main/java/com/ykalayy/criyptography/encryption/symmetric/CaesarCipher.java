package com.ykalayy.criyptography.encryption.symmetric;

import com.ykalayy.criyptography.encryption.CryptoAlgorithm;

public class CaesarCipher implements CryptoAlgorithm {
    public CaesarCipher() {
    }

    @Override
    public String encrypt(String plainText, Object key) {

        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Invalid privateKey");
        }

        StringBuilder sb = new StringBuilder();
        int privateShiftNumber = (int) key;
        if (plainText == null || plainText.equals("")) {
            return plainText;
        }

        for (int i = 0; i < plainText.length(); i++) {
            sb.append((char) (plainText.charAt(i) + privateShiftNumber));
        }

        return sb.toString();
    }

    @Override
    public String deCrypt(String cipherText, Object key) {

        if(!(key instanceof Integer)) {
            throw new IllegalArgumentException("Invalid privateKey");
        }

        StringBuilder sb = new StringBuilder();
        int privateShiftNumber = (int) key;
        if (cipherText == null || cipherText.equals("")) {
            return cipherText;
        }

        for (int i = 0; i < cipherText.length(); i++) {
            sb.append((char) (cipherText.charAt(i) - privateShiftNumber));
        }

        return sb.toString();
    }
}
