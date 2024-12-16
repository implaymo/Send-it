package send.it.GoogleApi;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GooglePeopleApiTest {

    @Mock
    private PeopleService peopleService;

    @Mock
    private PeopleService.People people;

    @Mock
    private PeopleService.People.Connections connections;

    @Mock
    private PeopleService.People.Connections.List list;

    @InjectMocks
    private PeopleApi googlePeopleApi;


    @Test
    void should_return_specified_number_of_contacts() throws IOException, GeneralSecurityException {
        // arrange
        ListConnectionsResponse response = new ListConnectionsResponse();

        Person person1 = new Person();
        Name name1 = new Name();
        name1.setDisplayName("John Doe");
        person1.setNames(Collections.singletonList(name1));

        Person person2 = new Person();
        Name name2 = new Name();
        name2.setDisplayName("Jane Smith");
        person2.setNames(Collections.singletonList(name2));

        Person person3 = new Person();
        Name name3 = new Name();
        name3.setDisplayName("Alice Johnson");
        person3.setNames(Collections.singletonList(name3));

        response.setConnections(Arrays.asList(person1, person2, person3));

        when(peopleService.people()).thenReturn(people);
        when(people.connections()).thenReturn(connections);
        when(connections.list("people/me")).thenReturn(list);
        when(list.setPageSize(3)).thenReturn(list);
        when(list.setPersonFields("names,emailAddresses")).thenReturn(list);
        when(list.execute()).thenReturn(response);

        // act
        List<Name> result = googlePeopleApi.getContacts(3);

        // assert
        assertEquals(3, result.size(), "The number of contacts should be 3");
        assertEquals("John Doe", result.get(0).getDisplayName(), "First contact's name should be John Doe");
        assertEquals("Jane Smith", result.get(1).getDisplayName(), "Second contact's name should be Jane Smith");
        assertEquals("Alice Johnson", result.get(2).getDisplayName(), "Third contact's name should be Alice Johnson");
    }

}
