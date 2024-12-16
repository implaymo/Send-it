package send.it.GoogleApi;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PeopleApi {

    private static final Logger logger = LoggerFactory.getLogger(PeopleApi.class);
    private static final String APPLICATION_NAME = "SendItGooglePeopleApi";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.CONTACTS_READONLY);
    private static final String TOKENS_DIRECTORY_PATH = "Peopletokens";

     public List<Name> getContacts(int numberOfContactsUserWants) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService service =
        new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, Credentials.getCredentials(HTTP_TRANSPORT, SCOPES, TOKENS_DIRECTORY_PATH))
            .setApplicationName(APPLICATION_NAME)
            .build();

        ListConnectionsResponse response = service.people().connections()
                .list("people/me")
                .setPageSize(numberOfContactsUserWants)
                .setPersonFields("names,emailAddresses")
                .execute();

        List<Person> connections = response.getConnections();
        List<Name> names = new ArrayList<>();
        if (connections != null && !connections.isEmpty()) {
            for (Person person : connections) {
                if (person.getNames() != null && !person.getNames().isEmpty()) {
                    System.out.println(person.getNames());
                    names.addAll(person.getNames());
                }
            }
        } else {
            logger.error("No connections found.");
        }
        return names;
    }

    public void searchUserContacts(String userSearchedContact) throws InterruptedException, IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService peopleService = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, Credentials.getCredentials(HTTP_TRANSPORT, SCOPES, TOKENS_DIRECTORY_PATH))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Warmup cache
        peopleService.people().searchContacts()
                .setQuery("")
                .setReadMask("names,emailAddresses")
                .execute();

        // Wait a few seconds
        Thread.sleep(5);

        // Send search request
        SearchResponse contacts = peopleService.people().searchContacts()
                .setQuery(userSearchedContact)
                .setReadMask("names,emailAddresses")
                .execute();

        System.out.println(contacts);
    }
}
