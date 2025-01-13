package send.it.Security;

import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordVerfication {
    
    static final Logger logger = LoggerFactory.getLogger(PasswordHashing.class);

    public PasswordVerfication(String inputPassword) {

    }

    
    
    public static boolean isPasswordInDatabase(String inputPassword, String storedHash, byte[] salt, int iterationCount, int keyLength) throws Exception {
        try {
            KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            String hashString = Base64.getEncoder().encodeToString(hash);
            return hashString.equals(storedHash);
        } catch (Exception e) {
            logger.error("ERROR: {}", e.getMessage(), e);
        }
        return false;
    }
}
