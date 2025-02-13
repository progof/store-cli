package com.store.app.util;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {
    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return hashedPassword.equals(hashPassword(rawPassword));
    }
}
