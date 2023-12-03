package programmingmagic.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Pbkdf2PasswordHash2 {


    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ITERATIONS = 10000;

    public String generate(char[] password) {
        try {
            return Base64.getEncoder().encodeToString(
                    SecretKeyFactory.getInstance(ALGORITHM)
                            .generateSecret(new PBEKeySpec(password, generateRandomSalt(), ITERATIONS, HASH_LENGTH))
                            .getEncoded()
            );
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean verify(char[] password, String hashedPassword) {
        return hashedPassword.equals(generate(password));
    }



    public static byte[] generateRandomSalt() {
        return new SecureRandom().generateSeed(SALT_LENGTH);
    }

}
