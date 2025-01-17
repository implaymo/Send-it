package send.it.Security;

import java.security.SecureRandom;

public class PasswordSalt {
    

    public static byte[] generateRandomSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
