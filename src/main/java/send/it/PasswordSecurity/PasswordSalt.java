package send.it.PasswordSecurity;

import java.security.SecureRandom;

public class PasswordSalt {

    public PasswordSalt() {

    }

    public byte[] generateRandomSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
