package send.it.GoogleApi;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import send.it.Time;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;


public class CalendarApi {
    static final Logger logger = LoggerFactory.getLogger(CalendarApi.class);

    private static final String APPLICATION_NAME = "SendItGoogleCalendarApi";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String TOKENS_DIRECTORY_PATH = "Calendartokens";

    
    public List<Event> getEvents() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, Credentials.getCredentials(HTTP_TRANSPORT, SCOPES, TOKENS_DIRECTORY_PATH))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> nextEventsOnCalendar = events.getItems();
        System.out.println("Upcoming events");
        for (Event event : nextEventsOnCalendar) {
            EventDateTime start = event.getStart();
            ZonedDateTime eventTime = Time.convertToZonedDateTime(start);
            if (Time.checkIfEventIsIn24Hours(eventTime)) {
                System.out.printf("%s (%s)\n", event.getSummary(), event.getStart().getDateTime());
            }
        }
        return nextEventsOnCalendar;
    }
}


