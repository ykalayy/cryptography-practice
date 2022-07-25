package com.ykalayy.criyptography.encryption.asymmetric;

import com.ykalayy.criyptography.encryption.CryptoAlgorithm;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA implements CryptoAlgorithm {

    private static final SecureRandom secureRandom = new SecureRandom();
    private final BigInteger publicKey;
    private final BigInteger privateKey;
    private final BigInteger n;

    /**
     *
     * @param p Big Prime Number
     * @param q Big Prime NUmber
     */
    public RSA(BigInteger p, BigInteger q) {

        n = p.multiply(q);

        BigInteger totientVal = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));

        publicKey = generatePublicKey(totientVal);
        privateKey = publicKey.modInverse(totientVal);
    }

    public String encrypt(String plainText, Object key) {
        if(key != null) {
            throw new IllegalArgumentException("Key is not supported for RSA algorithm");
        }

        BigInteger plainTextAsInt = new BigInteger(plainText.getBytes());

        return plainTextAsInt.modPow(publicKey, n).toString();
    }

    @Override
    public String deCrypt(String cipherText, Object key) {
        if(key != null) {
            throw new IllegalArgumentException("Key is not supported for RSA algorithm");
        }

        BigInteger cipherTextAsInt = new BigInteger(cipherText);

        return new String(cipherTextAsInt.modPow(privateKey, n).toByteArray());
    }

    private BigInteger generatePublicKey(BigInteger totientVal) {
        BigInteger possibleE = new BigInteger(totientVal.bitLength(), secureRandom);

        while(!possibleE.gcd(totientVal).equals(BigInteger.ONE)) {
            possibleE = new BigInteger(totientVal.bitLength(), secureRandom);
        }
        return possibleE;

    }
}
