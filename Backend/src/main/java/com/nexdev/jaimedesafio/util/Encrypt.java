package com.nexdev.jaimedesafio.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Encrypt {
    private static final String ALGORITH = "AES";
    private static final byte[] keyValue = "p1k1m1n!M4ster10".getBytes();

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGORITH);
    }

    public static String encrypt(String toEncrypt) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITH);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());

        byte[] encryptValue = cipher.doFinal(toEncrypt.getBytes());
        byte[] encryptedByteValue = new Base64().encode(new Base64().encode(new Base64().encode(encryptValue)));

        System.out.println("Valor criptografado: " + new String(encryptedByteValue));

        return new String(encryptValue);
    }
}
