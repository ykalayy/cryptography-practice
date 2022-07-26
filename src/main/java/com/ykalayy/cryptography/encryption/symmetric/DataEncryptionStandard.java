package com.ykalayy.cryptography.encryption.symmetric;

import com.ykalayy.cryptography.encryption.CryptoAlgorithm;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DataEncryptionStandard implements CryptoAlgorithm {


    private Cipher encryptionCipher;
    private Cipher decryptionCipher;
    private IvParameterSpec ivParameterSpec;


    public DataEncryptionStandard(SecretKey secretKey, byte[] iv) {

        // 64 bit block
        if(iv.length != 8) {
            throw new IllegalArgumentException("IV block should be 8 bytes");
        }

        try {
            encryptionCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptionCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // Initialization vector creation
            ivParameterSpec = new IvParameterSpec(iv);

            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String plainText, Object key) {

        if(key != null) {
            throw new IllegalArgumentException("Key is not supported for DES");
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
            throw new IllegalArgumentException("Key is not supported for DES");
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
