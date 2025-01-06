package send.it.Security;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordVerfication {
    
    static final Logger logger = LoggerFactory.getLogger(PasswordHashing.class);

    
    
    public static boolean verifyPassword(String inputPassword, String storedHash, byte[] salt, int iterationCount, int keyLength) throws Exception {
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);
        logger.info("IS PASSWORD VALID? {}", newHash.equals(storedHash));
        return newHash.equals(storedHash);
    }
}
