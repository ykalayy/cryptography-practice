package com.ykalayy.criyptography.encryption.asymmetric;

import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final SecretKeySpec sharedSecretKeySpec;

    public DiffieHellman(BigInteger g, BigInteger n) {
        this.sharedSecretKeySpec = this.generatePrivateKey(g, n);
    }

    private SecretKeySpec generatePrivateKey(BigInteger g, BigInteger n) {
        BigInteger x = BigInteger.valueOf(SECURE_RANDOM.nextInt(n.intValue() - 2) + 1);
        BigInteger y = BigInteger.valueOf(SECURE_RANDOM.nextInt(n.intValue() - 2) + 1);

        BigInteger k1 = calculateKey(x, g, n);
        BigInteger k2 = calculateKey(y, g, n);

        BigInteger sharedKey = calculateSharedKey(k1, x, n);
        return new SecretKeySpec(sharedKey.toByteArray(), "AES");
    }

    private BigInteger calculateSharedKey(BigInteger k1, BigInteger privateKey, BigInteger n) {
        return k1.modPow(privateKey, n);
    }

    private BigInteger calculateKey(BigInteger randomPrivateKey, BigInteger g, BigInteger n) {
        return g.modPow(randomPrivateKey, n);
    }
}
