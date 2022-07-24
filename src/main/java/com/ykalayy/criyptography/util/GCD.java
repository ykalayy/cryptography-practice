package com.ykalayy.criyptography.util;

public class GCD {

    /**
     *
     * @param e
     * @param z
     * @return
     */
    public static int gcd(int e, int z) {
        if(e % z == 0) {
            return z;
        }
        return gcd(z, e % z);
    }
}
