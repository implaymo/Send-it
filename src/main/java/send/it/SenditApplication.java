package send.it;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import send.it.Entity.Users;
import send.it.Security.PasswordSalt;
import send.it.Service.UsersService;




@SpringBootApplication
@EntityScan("send.it.Entity")  // Add this if your entities are in a different package
public class SenditApplication implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    public static void main(String[] args) {
        SpringApplication.run(SenditApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Users usersTable = new Users();
            // Don't set the ID
            usersTable.setBirthdate(LocalDate.of(1996, 12, 12));
            byte[] salt = PasswordSalt.generateRandomSalt();
            usersTable.setSalt(salt);
            usersTable.setPassword("lol12345678", salt);
            
            Users savedUser = usersService.saveUser(usersTable);
            System.out.println("User saved successfully with ID: " + savedUser.getId());
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}