package br.com.leonardoterrao.resources;

import br.com.leonardoterrao.dao.PersonDAO;
import br.com.leonardoterrao.model.Person;
import io.dropwizard.testing.junit.ResourceTestRule;
import junit.framework.TestCase;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonResourceTest extends TestCase {

    private static final PersonDAO DAO = Mockito.mock(PersonDAO.class);

    @ClassRule
    public static final ResourceTestRule RULE = ResourceTestRule.builder()
            .addResource(new PersonResource(DAO))
            .setTestContainerFactory(new GrizzlyTestContainerFactory())
            .build();

    private Person person;

    @Before
    public void beforeTest() {
        person = new Person();
        person.setId(1L);
    }

    @After
    public void afterTest() {
        Mockito.reset(DAO);
    }

    @Test
    public void getPersonSuccess() {
        Mockito.when(DAO.findById(1L)).thenReturn(Optional.of(person));

        Person found = RULE.getJerseyTest().target("/people/1").request().get(Person.class);

        assertThat(found.getId()).isEqualTo(person.getId());
        Mockito.verify(DAO).findById(1L);
    }

    @Test
    public void getPersonNotFound() {
        Mockito.when(DAO.findById(2L)).thenReturn(Optional.empty());
        final Response response = RULE.getJerseyTest().target("/people/2").request().get();

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
        Mockito.verify(DAO).findById(2L);
    }
}