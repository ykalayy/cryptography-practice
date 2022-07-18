package com.ykalayy.criyptography.encryption.symmetric;

import com.ykalayy.criyptography.encryption.CryptoAlgorithm;

import java.util.Random;

public class OneTimePad implements CryptoAlgorithm {

    public String encrypt(String plainText, Object keys) {

        if(!(keys instanceof int[])) {
            throw new IllegalArgumentException("Invalid privateKey. It should be int array list");
        }

        StringBuilder cipherText = new StringBuilder();
        int[] secretKeys = (int[]) keys;

        for(int i = 0; i < secretKeys.length; i++) {
            cipherText.append((char) (plainText.charAt(i) + secretKeys[i]));
        }

        return cipherText.toString();
    }

    @Override
    public String deCrypt(String cipherText, Object keys) {

        if(!(keys instanceof int[])) {
            throw new IllegalArgumentException("Invalid privateKey. It should be int array list");
        }

        StringBuilder plainText = new StringBuilder();
        int[] secretKeys = (int[]) keys;

        for(int i = 0; i < secretKeys.length; i++) {
            plainText.append((char) (cipherText.charAt(i) - secretKeys[i]));
        }

        return plainText.toString();
    }

    static class RandomGenerator {

        static Random rnd = new Random();

        public static int[] generateRnd(int size) {
            int[] randomIntegers = new int[size];

            for (int i = 0; i < randomIntegers.length; i++) {
                randomIntegers[i] = rnd.nextInt();
            }
            return randomIntegers;
        }
    }
}


