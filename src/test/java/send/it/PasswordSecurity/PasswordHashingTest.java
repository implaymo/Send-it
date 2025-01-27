package send.it.PasswordSecurity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PasswordHashingTest {
    @Test
    void shouldReturnHashedPassword() {
        //arrange
        String password = "ola123456";
        PasswordSalt passwordSalt = new PasswordSalt();
        byte[] salt = passwordSalt.generateRandomSalt();
        PasswordHashing passwordHashing = new PasswordHashing();

        //act
        String hashedPassword = passwordHashing.hashPassword(password, salt);
        //assert
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }


    @Test
    void shouldReturnNullIfPasswordNull() {
        //arrange
        String password = null;
        PasswordSalt passwordSalt = new PasswordSalt();
        byte[] salt = passwordSalt.generateRandomSalt();
        PasswordHashing passwordHashing = new PasswordHashing();

        //act
        String hashedPassword = passwordHashing.hashPassword(password, salt);
        //assert
        assertNull(hashedPassword);
    }

    @Test
    void shouldReturnNullIfSaltNull() {
        //arrange
        String password = "ola123456";
        byte[] salt = null;
        PasswordHashing passwordHashing = new PasswordHashing();

        //act
        String hashedPassword = passwordHashing.hashPassword(password, salt);
        //assert
        assertNull(hashedPassword);
    }

    @Test
    void shouldReturnNullIfSaltAndPasswordNull() {
        //arrange
        String password = null;
        byte[] salt = null;
        PasswordHashing passwordHashing = new PasswordHashing();

        //act
        String hashedPassword = passwordHashing.hashPassword(password, salt);
        //assert
        assertNull(hashedPassword);
    }
}
