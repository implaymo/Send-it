package send.it.DatabaseTables;


import java.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;
import org.passay.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import send.it.PasswordSecurity.PasswordHashing;
import send.it.PasswordSecurity.PasswordSalt;

import java.util.Arrays;

@Entity
@Table(name = "users")
public class UsersTable {
    // Interacts with users Database. Gets and Sends data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private byte[] salt;

    public UsersTable(String email, String username, String name, LocalDate birthdate, String password, byte[] salt) throws Exception {
        if (!isEmailValid(email)) {
            throw new Exception("Email is invalid.");
        }
        if(!isBirthdateValid(birthdate)) {
            throw new Exception("Birthdate is invalid.");
        }
        if(!isNameValid(name)) {
            throw new Exception("Name is invalid.");
        }
        if(!isUsernameValid(username)) {
            throw new Exception("Username is invalid.");
        }
        if(!isPasswordValid(password)){
            throw new Exception("Password is invalid.");
        }
        if(!isSaltValid(salt)) {
            throw new Exception("Salt is invalid.");
        }
        this.email = email;
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
        this.salt = salt;
    }

    public UsersTable() {

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    // Setters with validation
    public void setId(int id) {
        if (isIdValid(id)) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public void setEmail(String email) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void setUsername(String username) {
        if (isUsernameValid(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public void setName(String name) {
        if (isNameValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public void setBirthdate(LocalDate birthdate) {
        if (isBirthdateValid(birthdate)) {
            this.birthdate = birthdate;
        } else {
            throw new IllegalArgumentException("Invalid birthdate");
        }
    }

    public void setPassword(String password, PasswordHashing passwordHashing, PasswordSalt salt) {
        if (isPasswordValid(password)) {
            byte[] newSalt = salt.generateRandomSalt();
            setSalt(newSalt);
            this.password = PasswordHashing.hashPassword(password, newSalt);
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void setSalt(byte[] salt) {
        if (isSaltValid(salt)) {
            this.salt = salt;
        } else {
            throw new IllegalArgumentException("Invalid salt");
        }
    }

    // Validation methods
    private boolean isIdValid(int id) {
        return id >= 0;
    }

    private boolean isEmailValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    private boolean isUsernameValid(String username) {
        return username != null && !username.isBlank();
    }

    private boolean isNameValid(String name) {
        return name != null && !name.isBlank();
    }

    private boolean isBirthdateValid(LocalDate birthdate) {
        return birthdate != null && !birthdate.isAfter(LocalDate.now());
    }

    private boolean isPasswordValid(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 30),
            new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }

    private boolean isSaltValid(byte[] salt) {
        return salt != null && salt.length > 0;
    }
}