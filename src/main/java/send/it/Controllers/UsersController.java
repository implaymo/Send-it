package send.it.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import send.it.DatabaseTables.UsersTable;
import send.it.Repository.UsersRepository;
import send.it.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UsersTable> getAllUsers() {
        List<UsersTable> users = userService.getAllUsers();
        System.out.println("Found " + users.size() + " users");
        return users;
    }

    /*@PostMapping("/add")
    public UsersTable addUser(@RequestBody UsersTable user) {
        return usersRepository.save(user);
    }*/
}
