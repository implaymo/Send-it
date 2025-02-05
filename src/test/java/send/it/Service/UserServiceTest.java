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

}

