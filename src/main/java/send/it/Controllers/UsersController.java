package send.it.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import send.it.Dto.UserRequestDTO;
import send.it.Entity.Users;
import send.it.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {


    @Autowired
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        System.out.println("Found " + users.size() + " users");
        return users;
    }

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody UserRequestDTO request) throws Exception {
        try {
            Users savedUser = userService.registerUser(request);
            return ResponseEntity.ok(savedUser);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Return a 400 status code
        }
    }
}
