package com.ykalayy.cryptography.util;

public class ModularInverse {

    public static int findInverse(int a, int m) {
        for(int i = 0; i < m; i++) {
            if((a * i) % m == 1)
                return i;
        }

        return -1;
    }
}
