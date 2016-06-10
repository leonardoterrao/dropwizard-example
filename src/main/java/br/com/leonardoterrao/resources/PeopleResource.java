package br.com.leonardoterrao.resources;

import br.com.leonardoterrao.dao.PersonDAO;
import br.com.leonardoterrao.model.Person;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@AllArgsConstructor
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleResource.class);

    private final PersonDAO personDAO;

    @POST
    @UnitOfWork
    public Person createPerson(Person person) {
        LOGGER.info("Received new person: {}", person);
        return personDAO.create(person);
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople() {
        return personDAO.findAll();
    }

}
