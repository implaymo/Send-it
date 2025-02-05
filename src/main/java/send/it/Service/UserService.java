package send.it.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Users registerUser(Users users) throws Exception {

        if(isUserInDatabase(users.getEmail())) {
            throw new Exception("User already exists in database.");
        }
        users.setSalt(passwordSalt);
        users.setPassword(users.getPassword(), passwordHashing);

        return usersRepository.save(users);
    }

    public boolean isUserInDatabase(String email) {
        Optional<Users> existingUser = usersRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return true;
        }
        return false;
    }
    
}
