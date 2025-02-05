package send.it.Entity;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
public class Users {

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

    public void setPassword(String password, PasswordHashing passwordHashing) {
        String hashedPassword = passwordHashing.hashPassword(password, this.salt);
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            throw new IllegalStateException("Password hashing failed");
        }
        this.password = hashedPassword;  // Set the hashed password
    }

    public void setSalt(PasswordSalt passwordSalt) {
        byte[] newSalt = passwordSalt.generateRandomSalt();
        System.out.println("Generated Salt: " + Arrays.toString(newSalt));  // Debugging output

        if (newSalt == null || newSalt.length == 0) {
            throw new IllegalStateException("Generated salt should never be null or empty");
        }
        this.salt = newSalt;
    }
}
