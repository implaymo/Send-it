package send.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import send.it.GoogleApi.GoogleCalendarApi;
import send.it.GoogleApi.GooglePeopleApi;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootApplication
public class SenditApplication {

	public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException {
		SpringApplication.run(SenditApplication.class, args);
		GoogleCalendarApi.getEvents();
	}
}