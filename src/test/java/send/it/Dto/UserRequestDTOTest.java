package send.it.Dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestDTOTest {

    @Test
    void shouldReturnValidObjectForConstrutorWithNoParameters() {
        // arrange
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO();
        // assert
        assertNotNull(userRequestDto);
    }

    @Test
    void shouldReturnEmailOfCreatedDto(){
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        // assert
        assertThat(userRequestDto.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldReturnUsernameOfCreatedDto(){
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        // assert
        assertThat(userRequestDto.getUsername()).isEqualTo("john123");
    }

    @Test
    void shouldReturnNameOfCreatedDto() {
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        // assert
        assertThat(userRequestDto.getName()).isEqualTo("John");
    }

    @Test
    void shouldReturnBirthdateOfCreateDto() {
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        // assert
        assertThat(userRequestDto.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    void shouldReturnPasswordOfCreatedDto() {
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserRequestDTO userRequestDto = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        // assert
        assertThat(userRequestDto.getPassword()).isEqualTo("password123");
    }

    @Test
    void shouldReturnEmailSetted(){
        // arrange
        UserRequestDTO userRequestDto = new UserRequestDTO();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userRequestDto.setEmail("jane@example.com");
        userRequestDto.setUsername("jane123");
        userRequestDto.setName("Jane");
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setPassword("password456");
        //assert
        assertThat(userRequestDto.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void shouldReturnUsernameSetted() {
        // arrange
        UserRequestDTO userRequestDto = new UserRequestDTO();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userRequestDto.setEmail("jane@example.com");
        userRequestDto.setUsername("jane123");
        userRequestDto.setName("Jane");
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setPassword("password456");
        //assert
        assertThat(userRequestDto.getUsername()).isEqualTo("jane123");
    }

    @Test
    void shouldReturnNameSetted() {
        // arrange
        UserRequestDTO userRequestDto = new UserRequestDTO();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userRequestDto.setEmail("jane@example.com");
        userRequestDto.setUsername("jane123");
        userRequestDto.setName("Jane");
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setPassword("password456");
        //assert
        assertThat(userRequestDto.getName()).isEqualTo("Jane");
    }

    @Test
    void shouldReturnBirthdateSetted() {
        // arrange
        UserRequestDTO userRequestDto = new UserRequestDTO();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userRequestDto.setEmail("jane@example.com");
        userRequestDto.setUsername("jane123");
        userRequestDto.setName("Jane");
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setPassword("password456");
        //assert
        assertThat(userRequestDto.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    void shouldReturnPasswordSetted() {
        // arrange
        UserRequestDTO userRequestDto = new UserRequestDTO();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userRequestDto.setEmail("jane@example.com");
        userRequestDto.setUsername("jane123");
        userRequestDto.setName("Jane");
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setPassword("password456");
        //assert
        assertThat(userRequestDto.getPassword()).isEqualTo("password456");
    }

    @Test
    void shouldCheckIfTwoObjectsAreEqual() {
        //arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        //act
        UserRequestDTO user1 = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        UserRequestDTO user2 = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        UserRequestDTO user3 = new UserRequestDTO("jane@example.com", "jane123", "Jane", LocalDate.of(2000, 1, 15), "password456");

        //assert
        assertThat(user1).isEqualTo(user2);
        assertThat(user1).isNotEqualTo(user3);
    }

    @Test
    void shouldCheckIfHashCodesAreTheSame() {
        //arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        //act
        UserRequestDTO user1 = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");
        UserRequestDTO user2 = new UserRequestDTO("john@example.com", "john123", "John", birthdate, "password123");

        //assert
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }
}