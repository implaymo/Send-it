package send.it.Dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {


    @Test
    void shouldReturnValidObjectForConstrutorWithNoParameters() {
        // arrange
        // act
        UserDto userDto = new UserDto();
        // assert
        assertNotNull(userDto);
    }

    @Test
    void shouldReturnEmailOfCreatedDto(){
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserDto userDto = new UserDto("john@example.com", "john123", "John", birthdate);
        // assert
        assertThat(userDto.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldReturnUsernameOfCreatedDto(){
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserDto userDto = new UserDto("john@example.com", "john123", "John", birthdate);
        // assert
        assertThat(userDto.getUsername()).isEqualTo("john123");
    }

    @Test
    void shouldReturnNameOfCreatedDto() {
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserDto userDto = new UserDto("john@example.com", "john123", "John", birthdate);
        // assert
        assertThat(userDto.getName()).isEqualTo("John");
    }

    @Test
    void shouldReturnBirthdateOfCreateDto() {
        // arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        // act
        UserDto userDto = new UserDto("john@example.com", "john123", "John", birthdate);
        // assert
        assertThat(userDto.getBirthdate()).isEqualTo(birthdate);
    }


    @Test
    void shouldReturnEmailSetted(){
        // arrange
        UserDto userDto = new UserDto();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userDto.setEmail("jane@example.com");
        userDto.setUsername("jane123");
        userDto.setName("Jane");
        userDto.setBirthdate(birthdate);
        //assert
        assertThat(userDto.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void shouldReturnUsernameSetted() {
        // arrange
        UserDto userDto = new UserDto();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userDto.setEmail("jane@example.com");
        userDto.setUsername("jane123");
        userDto.setName("Jane");
        userDto.setBirthdate(birthdate);
        //assert
        assertThat(userDto.getUsername()).isEqualTo("jane123");
    }

    @Test
    void shouldReturnNameSetted() {
        // arrange
        UserDto userDto = new UserDto();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userDto.setEmail("jane@example.com");
        userDto.setUsername("jane123");
        userDto.setName("Jane");
        userDto.setBirthdate(birthdate);
        //assert
        assertThat(userDto.getName()).isEqualTo("Jane");
    }

    @Test
    void shouldReturnBirthdateSetted() {
        // arrange
        UserDto userDto = new UserDto();
        LocalDate birthdate = LocalDate.of(2000, 1, 15);
        //act
        userDto.setEmail("jane@example.com");
        userDto.setUsername("jane123");
        userDto.setName("Jane");
        userDto.setBirthdate(birthdate);
        //assert
        assertThat(userDto.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    void shouldCheckIfTwoObjectsAreEqual() {
        //arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        //act
        UserDto user1 = new UserDto("john@example.com", "john123", "John", birthdate);
        UserDto user2 = new UserDto("john@example.com", "john123", "John", birthdate);
        UserDto user3 = new UserDto("jane@example.com", "jane123", "Jane", LocalDate.of(2000, 1, 15));

        //assert
        assertThat(user1).isEqualTo(user2);
        assertThat(user1).isNotEqualTo(user3);
    }

    @Test
    void shouldCheckIfHashCodesAreTheSame() {
        //arrange
        LocalDate birthdate = LocalDate.of(1995, 5, 20);
        //act
        UserDto user1 = new UserDto("john@example.com", "john123", "John", birthdate);
        UserDto user2 = new UserDto("john@example.com", "john123", "John", birthdate);

        //assert
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());

    }


}