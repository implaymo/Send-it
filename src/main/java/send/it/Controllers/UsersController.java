package send.it.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import send.it.DatabaseTables.UsersTable;
import send.it.Repository.UsersRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<UsersTable> getAllUsers() {
        List<UsersTable> users = usersRepository.findAll();
        System.out.println("Found " + users.size() + " users");
        return users;
    }

    @PostMapping("/add")
    public UsersTable addUser(@RequestBody UsersTable user) {
        return usersRepository.save(user);
    }
}
