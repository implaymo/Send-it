package send.it;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenditApplication {

	public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
		SpringApplication.run(SenditApplication.class, args);
	}
}