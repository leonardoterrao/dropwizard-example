package br.com.leonardoterrao.resources;

import br.com.leonardoterrao.dao.PersonDAO;
import br.com.leonardoterrao.model.Person;
import br.com.leonardoterrao.view.PersonView;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@AllArgsConstructor
@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    private final PersonDAO personDAO;

    @GET
    @UnitOfWork
    public Person getPerson(@PathParam("personId") LongParam personId) {
        return findSafely(personId.get());
    }

    @GET
    @Path("/view_freemarker")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewFreemarker(@PathParam("personId") LongParam personId) {
        return new PersonView(PersonView.Template.FREEMARKER, findSafely(personId.get()));
    }

    @GET
    @Path("/view_mustache")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewMustache(@PathParam("personId") LongParam personId) {
        return new PersonView(PersonView.Template.MUSTACHE, findSafely(personId.get()));
    }

    @GET
    @Path("/newRandomPerson")
    @UnitOfWork
    public Person getRandomPerson() {
        Person randomPerson = personDAO.create(Person.builder()
                .name("RANDOM " + new Random().nextInt() * 33)
                .job("JOB " + new Random().nextInt() * 33)
                .build());

        LOGGER.info("Generated random person: {}", randomPerson);
        return randomPerson;
    }

    private Person findSafely(long personId) {
        return personDAO.findById(personId).orElseThrow(() -> new NotFoundException("No such user."));
    }

}
