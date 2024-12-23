package com.segurosbolivar.siniestros.config;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class Utilities {

    private static final String AES_CIPHER_ALGORITHM = "AES";
    private static final String AES_SECRET_KEY = "Ph4nt0m9q1w2ef3r"; // Debes gestionar las claves de manera segura


    public static class Encriptador {
        public String decryptData(String encryptedData) throws Exception {
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            String Dec = new String(decryptedBytes);
            return Dec;
        }
    }



}
