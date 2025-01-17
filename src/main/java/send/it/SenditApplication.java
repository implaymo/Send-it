package send.it;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import send.it.GoogleApi.CalendarApi;
import send.it.GoogleApi.PeopleApi;



@SpringBootApplication
public class SenditApplication {

	public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
		SpringApplication.run(SenditApplication.class, args);

		CalendarApi calendarApi = new CalendarApi();
		PeopleApi peopleApi = new PeopleApi();
		peopleApi.getContacts(10);
		calendarApi.getEvents();
	}
}