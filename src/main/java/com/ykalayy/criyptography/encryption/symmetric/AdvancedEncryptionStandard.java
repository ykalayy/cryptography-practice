package com.ykalayy.criyptography.encryption.symmetric;

import com.ykalayy.criyptography.encryption.CryptoAlgorithm;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AdvancedEncryptionStandard implements CryptoAlgorithm {

    private final Cipher encryptionCipher;
    private final Cipher decryptionCipher;

    public AdvancedEncryptionStandard(SecretKey secretKey, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {

        // 128 bit block
        if(iv.length != 16) {
            throw new IllegalArgumentException("IV block should be 16 bytes");
        }

        // Block by Block
        // 1-2-3-4 - (XOR)
        this.encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        this.encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        this.decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
    }

    @Override
    public String encrypt(String plainText, Object key) {
        if(key != null) {
            throw new IllegalArgumentException("Key is not supported for AES implementation");
        }

        byte[] plainTextAsyByte = plainText.getBytes();
        byte[] cipherText = null;
        try {
            cipherText = encryptionCipher.doFinal(plainTextAsyByte);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return cipherText == null
                ? null
                : Base64.getEncoder().encodeToString(cipherText);
    }

    @Override
    public String deCrypt(String cipherText, Object key) {
        if(key != null) {
            throw new IllegalArgumentException("Key is not supported for AES implementation");
        }

        byte[] cipherTextAsByte = Base64.getDecoder().decode(cipherText.getBytes());
        byte[] plainText = null;
        try {
            plainText = decryptionCipher.doFinal(cipherTextAsByte);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return plainText == null
                ? null
                : new String(plainText);
    }
}
