package send.it.Entity;


import java.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;
import org.passay.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import send.it.Security.PasswordHashing;

import java.util.Arrays;


@Entity
@Table(name = "users")
public class Users {
    // Interacts with users Database. Gets and Sends data

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int _id;

    @Column(name = "email")
    private String _email;

    @Column(name = "username")
    private String _username;

    @Column(name = "name")
    private String _name;

    @Column(name = "birthdate")
    private LocalDate _birthdate;

    @Column(name = "password")
    private String _password;

    @Column(name = "salt")
    private byte[] _salt;

    public Users() {}


    public int getId() {
        return _id;
    }
    
    public String getEmail() {
        return _email;
    }
    
    public LocalDate getBirthdate() {
        return _birthdate;
    }
    
    public String getPassword() {
        return _password;
    }
    
    public byte[] getSalt() {
        return _salt;
    }


    // Setters with validation
    public void setId(int id) {
        if (isIdValid(id)) {
            this._id = id;
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public void setEmail(String email) {
        if (isEmailValid(email)) {
            this._email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void setUsername(String username) {
        if (isUsernameValid(username)) {
            this._username = username;
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public void setName(String name) {
        if (isNameValid(name)) {
            this._name = name;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public void setBirthdate(LocalDate birthdate) {
        if (isBirthdateValid(birthdate)) {
            this._birthdate = birthdate;
        } else {
            throw new IllegalArgumentException("Invalid birthdate");
        }
    }

    public void setPassword(String password, byte[] salt) {
        if (isPasswordValid(password)) {
            this._password = PasswordHashing.hashPasswordAndSetUserSalt(password, salt);
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void setSalt(byte[] salt) {
        if (isSaltValid(salt)) {
            this._salt = salt;
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