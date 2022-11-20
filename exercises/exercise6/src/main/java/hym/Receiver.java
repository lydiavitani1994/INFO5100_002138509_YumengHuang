package hym;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class Receiver {
    private SecretKey secretKey;
    private PrivateKey privateKey;
    private final SecureRandom secureRandom = new SecureRandom();

    public Receiver(SecretKey secretKey, PrivateKey privateKey) {
        this.secretKey = secretKey;
        this.privateKey = privateKey;
    }

    public void getMessageAES(byte[] encryptedMessage) throws Exception {
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        AlgorithmParameterSpec gcmIv = new GCMParameterSpec(128, encryptedMessage, 0, 12);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmIv);

        byte[] plainTextBytes = cipher.doFinal(encryptedMessage, 12, encryptedMessage.length - 12);

        String plainText = new String(plainTextBytes, StandardCharsets.UTF_8);
        System.out.format("Received message: \"%s\"\n", plainText);
    }

    public void getMessageRSA(byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedTextArray = cipher.doFinal(encryptedMessage);

        String plainText = new String(decryptedTextArray);
        System.out.format("Received message: \"%s\"\n", plainText);
    }
}
