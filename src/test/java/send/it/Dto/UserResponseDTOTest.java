package send.it.Dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDTOTest {


    @Test
    void shouldCreateUserResponseDTOObject() {
        //arrange
        //act
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        //assert
        assertNotNull(userResponseDTO);
    }

    @Test
    void shouldReturnTrueIfEmailFromDatabaseIsTheSame() {
        // arrange
        String expectedEmail = "test@example.com";
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        // act
        userResponseDTO.setEmail(expectedEmail);
        String actualEmail = userResponseDTO.getEmail();

        // assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void shouldSetAndGetAllFieldsCorrectly() {
        // arrange
        String email = "test@example.com";
        String username = "testuser";
        String name = "Test User";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        UserResponseDTO userResponseDTO = new UserResponseDTO(email, username, name, birthdate);

        // act & assert
        assertEquals(email, userResponseDTO.getEmail());
        assertEquals(username, userResponseDTO.getUsername());
        assertEquals(name, userResponseDTO.getName());
        assertEquals(birthdate, userResponseDTO.getBirthdate());
    }

}