//package send.it.Service;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.security.SecureRandom;
//import java.time.LocalDate;
//
//import org.apache.catalina.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import send.it.Dto.UsersDto;
//import send.it.PasswordSecurity.PasswordHashing;
//import send.it.PasswordSecurity.PasswordSalt;
//import send.it.Repository.UsersRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    UsersRepository usersRepository;
//
//    @InjectMocks
//    UserService userService;
//
//    @Test
//    void shouldRegisterUser() throws Exception {
//        // Arrange
//        LocalDate birthdate = LocalDate.of(1990, 1, 1);
//        UsersDto usersDto = UsersDto.builder()
//                .email("test@example.com")
//                .username("testuser")
//                .birthdate(birthdate)
//                .name("Raul")
//                .password("password123")
//                .build();
//
//        when(usersRepository.save(Mockito.any(UsersDto.class))).thenReturn(usersDto);
//
//        // Act
//        UsersDto usersDto1 = userService.registerUser(usersDto);
//
//        // Assert
//        Assertions.assertThat(usersDto1).isNotNull();
//    }
//}
