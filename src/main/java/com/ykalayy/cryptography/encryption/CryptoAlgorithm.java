package com.ykalayy.cryptography.encryption;

public interface CryptoAlgorithm {

    String encrypt(String plainText, Object key);

    String deCrypt(String cipherText, Object key);
}
