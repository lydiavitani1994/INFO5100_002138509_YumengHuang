package hym;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SecureRandom secureRandom = new SecureRandom();

        // Message transfer with AES
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        // Message transfer with RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Sender alice = new Sender(secretKey, publicKey);
        Receiver bob = new Receiver(secretKey, privateKey);

        alice.sendMessageAES("Hello Bob AES!", bob);
        alice.sendMessageRSA("Hello Bob RSA!", bob);
    }
}
