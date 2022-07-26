package com.ykalayy.cryptography.encryption.symmetric;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * ??
 * TODO: Cipher Block Chaining
 */
public final class SymmetricEncryption {

    /**
     * Full name of AES is "Advanced Encryption Standard"
     * 128 bit block
     * Better than DES which is 64 bits
     */
    private static final String ENCRYPTION_STANDARD = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 192;
    private static final SecretKey SECRET_KEY;
    private static final String SUPER_PRIVATE_KEY = "key";

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_STANDARD);
            keyGenerator.init(KEY_SIZE);
            SECRET_KEY = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private SymmetricEncryption() {
    }


    /**
     *
     * @param input
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] random = new byte[16];
        secureRandom.nextBytes(random);
        // Initialization Vector instance
        IvParameterSpec ivSpec = new IvParameterSpec(random);
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY, ivSpec);
        return new String(cipher.doFinal(input.getBytes()));
    }

    public static String decrypt(String encryptedInput) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
        return new String(cipher.doFinal(encryptedInput.getBytes()));
    }
}
