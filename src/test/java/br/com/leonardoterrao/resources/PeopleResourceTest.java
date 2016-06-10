package br.com.leonardoterrao.resources;

import br.com.leonardoterrao.dao.PersonDAO;
import br.com.leonardoterrao.model.Person;
import com.google.common.collect.ImmutableList;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleResourceTest {

    private static final PersonDAO personDAO = mock(PersonDAO.class);

    @ClassRule
    public static final ResourceTestRule resource = ResourceTestRule
            .builder()
            .addResource(new PeopleResource(personDAO))
            .build();

    @Captor
    private ArgumentCaptor<Person> personCaptor;
    private Person person;

    @Before
    public void setup() {
        person = Person.builder().name("Leonardo").job("Developer").build();
    }

    @After
    public void destroy() {
        Mockito.reset(personDAO);
    }

    @Test
    public void testCreatePerson() {
        when(personDAO.create(any(Person.class))).thenReturn(person);
        final Response response = resource.client().target("/people").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(personDAO).create(personCaptor.capture());
        assertThat(personCaptor.getValue()).isEqualTo(person);
    }

    @Test
    public void testListPeople() {
        final ImmutableList<Person> people = ImmutableList.of(person);
        when(personDAO.findAll()).thenReturn(people);

        final List<Person> response = resource.client().target("/people").request()
                .get(new GenericType<List<Person>>() {});

        verify(personDAO).findAll();
        assertThat(response).containsAll(people);
    }

}
