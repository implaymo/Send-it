package send.it.Service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import send.it.Entity.Users;
import send.it.PasswordSecurity.PasswordHashing;
import send.it.PasswordSecurity.PasswordSalt;
import send.it.Repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordHashing passwordHashing;

    @Mock
    private PasswordSalt passwordSalt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Create the UserService instance and inject mocked dependencies
        userService = new UserService();
        userService.usersRepository = usersRepository;
        userService.passwordHashing = passwordHashing;
        userService.passwordSalt = passwordSalt;
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        // Arrange
        Users users = Users.builder()
                .email("test@example.com")
                .username("testuser")
                .name("Test User")
                .birthdate(LocalDate.of(1990, 1, 1))
                .password("plainPassword")
                .build();

        byte[] mockSalt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8}; // Mock salt
        String hashedPassword = "hashedPassword";

        // Mock repository and utility methods
        when(usersRepository.findByEmail(users.getEmail())).thenReturn(Optional.empty());
        when(passwordSalt.generateRandomSalt()).thenReturn(mockSalt);
        when(passwordHashing.hashPassword(users.getPassword(), mockSalt)).thenReturn(hashedPassword);
        when(usersRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Simulate save

        // Act
        Users result = userService.registerUser(users);

        // Assert
        assertNotNull(result.getPassword(), "Password should be set");
        assertArrayEquals(mockSalt, result.getSalt(), "Salt should match mock salt");
        assertEquals(hashedPassword, result.getPassword(), "Password should match hashed password");
        verify(usersRepository).save(any(Users.class)); // Ensure save was called
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        // Arrange
        Users users = Users.builder()
                .email("test@example.com")
                .username("testuser")
                .name("Test User")
                .birthdate(LocalDate.of(1990, 1, 1))
                .password("password123")
                .build();

        when(usersRepository.findByEmail(users.getEmail())).thenReturn(Optional.of(users));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            userService.registerUser(users);
        });

        assertEquals("User already exists in database.", exception.getMessage());
        verify(usersRepository, never()).save(any(Users.class));
    }
}

