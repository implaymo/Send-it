package send.it.DatabaseTables;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.apache.commons.validator.EmailValidator;
import org.passay.CharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.UsernameRule;
import org.passay.WhitespaceRule;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Entity
@Table(name = "users")
public class UsersTable {
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


    // Getters
    private int getId() {
        return  _id;
    }

    private String getEmail() {
        return _email;
    }

    private String getUsername () {
        return _username;
    }

    private String getName() {
        return _name;
    }

    private LocalDate getBirthdate() {
        return _birthdate;
    }

    private String getPassword () {
        return _password;
    }

    private byte[] getSalt() {
        return _salt;
    }

    private boolean isIdValid(int id) {
        if (id < 0) {
            return false;
        } 
        return true;
    }

    @SuppressWarnings("deprecation")
    private boolean isEmailValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    private boolean isUsernameValid(String username) {
        if (username == null || username.isBlank()) {
            return false;
        }
        return true;
    }

    private boolean isNameValid(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        return true;
    }


    private boolean isBirthdateValid(LocalDate birthdate) {
        if (birthdate.equals(null)) {
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 50),
            new UsernameRule(),
            new WhitespaceRule() 
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }

    private boolean isSaltValid(byte[] salt) {
        return salt != null && salt.length > 0;
    }
}