package send.it.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import send.it.Dto.UserDto;
import send.it.Entity.Users;
import send.it.PasswordSecurity.PasswordHashing;
import send.it.PasswordSecurity.PasswordSalt;
import send.it.Repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordHashing passwordHashing;
    
    @Autowired
    PasswordSalt passwordSalt;


    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users registerUser(UserDto userDto) throws Exception {
        if (isUserInDatabase(userDto.getEmail())) {
            throw new Exception("User already exists in database.");
        }
        Users user = Users.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .name(userDto.getName())
                .birthdate(userDto.getBirthdate())
                .build();

        user.setSalt(passwordSalt);
        user.setPassword(userDto.getPassword(), passwordHashing);

        return usersRepository.save(user);
    }

    private boolean isUserInDatabase(String email) {
        Optional<Users> existingUser = usersRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return true;
        }
        return false;
    }
    
}
