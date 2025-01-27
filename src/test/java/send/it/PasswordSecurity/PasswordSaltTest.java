package send.it.PasswordSecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PasswordSaltTest {

    @Test
    void shouldReturnValidSalt() {
        //arrange
        PasswordSalt passwordSalt = new PasswordSalt();
        //act
        byte[] salt = passwordSalt.generateRandomSalt();
        //assert
        assertNotNull(salt);
        assertEquals(16, salt.length); 
    }

    @Test
    void shouldGenerateDifferentSalts() {
        // Arrange
        PasswordSalt passwordSalt = new PasswordSalt();
        
        // Act
        byte[] salt1 = passwordSalt.generateRandomSalt();
        byte[] salt2 = passwordSalt.generateRandomSalt();
        
        // Assert
        assertNotNull(salt1);
        assertNotNull(salt2);
        assertEquals(16, salt1.length);
        assertEquals(16, salt2.length);
        assertNotEquals(salt1, salt2); 
    }
}
