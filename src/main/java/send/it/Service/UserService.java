package send.it.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import send.it.DatabaseTables.UsersTable;
import send.it.Repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public List<UsersTable> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersTable addUser() {
        UsersTable newUser = new UsersTable();
        return newUser;
    }
    
}
