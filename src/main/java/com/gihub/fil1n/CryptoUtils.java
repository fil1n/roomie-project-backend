package com.gihub.fil1n;

import org.mindrot.jbcrypt.BCrypt;

public class CryptoUtils {

    public static String cryptString(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static boolean isEqual(String plainText, String hashed) {
        if(BCrypt.checkpw(plainText, hashed)) {
            return true;
        }

        return false;
    }

    private CryptoUtils() {}
}
