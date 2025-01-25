package send.it.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import send.it.DatabaseTables.UsersTable;
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

    public List<UsersTable> getAllUsers() {
        return usersRepository.findAll();
    }

     public UsersTable registerUser(String email, String username, String name, LocalDate birthdate, String password) {
        UsersTable newUser = new UsersTable();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setBirthdate(birthdate);
        newUser.setPassword(password, passwordHashing, passwordSalt);
        return usersRepository.save(newUser);
    }
    
}
