package send.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootApplication
public class SenditApplication {

	public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException {
		SpringApplication.run(SenditApplication.class, args);
		GooglePeopleApi.searchUserContacts();
	}
}