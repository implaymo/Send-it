package send.it.PasswordSecurity;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class PasswordSalt {
    

    public PasswordSalt() {

    }

    public byte[] generateRandomSalt() {
        try {
            byte[] salt = new byte[16]; // Adjust the size of the salt as needed
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt); // Generates random bytes
            return salt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
