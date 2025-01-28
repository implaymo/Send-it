package send.it.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import send.it.Dto.UsersDto;
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

    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersDto registerUser(UsersDto usersDto) throws Exception {
        // If needed, ensure password is hashed before saving
        if(isUserInDatabase(usersDto.getEmail())) {
            throw new Exception("User already exists in database.");
        }
        if (usersDto.getPassword() != null && !usersDto.getPassword().isEmpty()) {
            usersDto.setPassword(usersDto.getPassword(), passwordHashing, passwordSalt);
        }
        return usersRepository.save(usersDto);
    }

    public boolean isUserInDatabase(String email) {
        Optional<UsersDto> existingUser = usersRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return true;
        }
        return false;
    }
    
}
