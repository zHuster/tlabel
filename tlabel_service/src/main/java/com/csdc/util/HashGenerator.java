package com.csdc.util;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;

import java.security.SecureRandom;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2016/9/22</pre>
 */
public class HashGenerator {
    public static String generateHexHash(int size) {
        if (size < 8) {
            size = 8;
        }
        if (size > 32) {
            size = 32;
        }
        byte[] tokenBytes = new byte[size];
        new SecureRandom().nextBytes(tokenBytes);
        return new String(Hex.encode(tokenBytes));
    }

    public static String generateBase64Hash(int size) {
        if (size < 8) {
            size = 8;
        }
        if (size > 32) {
            size = 32;
        }
        byte[] tokenBytes = new byte[size];
        new SecureRandom().nextBytes(tokenBytes);
        return new String(Base64.encode(tokenBytes));
    }
}
