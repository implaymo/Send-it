package send.it.GoogleApi;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.SearchResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GooglePeopleApi {

    private static final String APPLICATION_NAME = "SendItGooglePeopleApi";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.CONTACTS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private final PeopleService peopleService;



    public GooglePeopleApi() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.peopleService = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = GooglePeopleApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void getContacts() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService service =
                new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        // Request 10 connections.
        ListConnectionsResponse response = service.people().connections()
                .list("people/me")
                .setPageSize(10)
                .setPersonFields("names,emailAddresses")
                .execute();


        // Print display name of connections if available.
        List<Person> connections = response.getConnections();
        if (connections != null && connections.size() > 0) {
            for (Person person : connections) {
                List<Name> names = person.getNames();
                if (names != null && names.size() > 0) {
                    System.out.println("Name: " + person.getNames().get(0)
                            .getDisplayName());
                } else {
                    System.out.println("No names available for connection.");
                }
            }
        } else {
            System.out.println("No connections found.");
        }
    }

    public SearchResponse searchUserContacts(String userSearchedContact) throws InterruptedException, IOException, GeneralSecurityException {
        // Warmup cache
        SearchResponse response = peopleService.people().searchContacts()
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
        return contacts;
    }
}

