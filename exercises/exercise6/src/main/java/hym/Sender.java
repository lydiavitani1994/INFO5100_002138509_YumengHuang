package hym;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;

public class Sender {
    private SecretKey secretKey;
    private PublicKey publicKey;
    private final SecureRandom secureRandom = new SecureRandom();

    public Sender(SecretKey secretKey, PublicKey publicKey) {
        this.secretKey = secretKey;
        this.publicKey = publicKey;
    }

    public byte[] sendMessageAES(String plainText, Receiver receiver) throws Exception {
        byte[] iv = new byte[12];
        secureRandom.nextBytes(iv);
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);

        System.out.format("Sending message: \"%s\" as \"%s\"\n", plainText, Arrays.toString(byteBuffer.array()));
        receiver.getMessageAES(byteBuffer.array());
        return byteBuffer.array();
    }

    public byte[] sendMessageRSA(String plainText, Receiver receiver) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        System.out.format("Sending message: \"%s\" as \"%s\"\n", plainText, Arrays.toString(cipherText));
        receiver.getMessageRSA(cipherText);

        return cipherText;
    }
}
