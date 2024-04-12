package com.example.server.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author duchieu212
 */
public class GenSignatural512 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
        keyGen.init(512);
        SecretKey secretKey = keyGen.generateKey();
        byte[] secretKeyBytes = secretKey.getEncoded();
        System.err.println(Base64.getEncoder().encodeToString(secretKeyBytes));
    }
}
