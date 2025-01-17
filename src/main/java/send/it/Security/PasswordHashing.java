package send.it.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHashing {

    static final Logger logger = LoggerFactory.getLogger(PasswordHashing.class);
    private static final Integer iterationCount = 65536;
    private static final Integer keyLenght = 256;


    public static String hashPasswordAndSetUserSalt(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLenght);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            logger.error("ERROR: {}", String.valueOf(e));
        }
        return null;
    }


}
