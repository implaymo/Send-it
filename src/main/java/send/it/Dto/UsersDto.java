package send.it.Dto;


import java.time.LocalDate;

import com.google.auto.value.AutoValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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

@Getter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Birthdate is required")
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private byte[] salt;

    // Use a builder for cleaner initialization
    public static class UsersDtoBuilder {
        private String password;
        private byte[] salt;

        public UsersDtoBuilder password(String password, PasswordHashing passwordHashing, PasswordSalt saltGenerator) {
            this.salt = saltGenerator.generateRandomSalt();
            this.password = passwordHashing.hashPassword(password, this.salt);
            return this;
        }
    }

    // Custom password handling logic
    public void setPassword(String password, PasswordHashing passwordHashing, PasswordSalt saltGenerator) {
        byte[] newSalt = saltGenerator.generateRandomSalt();
        this.salt = newSalt;
        this.password = passwordHashing.hashPassword(password, newSalt);
    }
}
