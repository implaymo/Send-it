package send.it;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import send.it.GoogleApi.PeopleApi;



@SpringBootApplication
public class SenditApplication {

	public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
		SpringApplication.run(SenditApplication.class, args);
		PeopleApi peopleApi = new PeopleApi();
		peopleApi.getContacts(10);
	}
}