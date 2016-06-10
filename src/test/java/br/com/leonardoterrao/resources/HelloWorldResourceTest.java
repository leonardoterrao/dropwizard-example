package br.com.leonardoterrao.resources;

import br.com.leonardoterrao.api.Saying;
import br.com.leonardoterrao.template.Template;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.GenericType;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldResourceTest {

    @ClassRule
    public static final ResourceTestRule resouces = ResourceTestRule.builder()
            .addResource(new HelloWorldResource(new Template("Hello, %s!", "Fulano")))
            .build();

    @Test
    public void sayHello() {
        final Saying response = resouces.client().target("/hello-world").request().get(new GenericType<Saying>() {});
        assertThat(response.getContent()).isEqualTo("Hello, Fulano!");
    }

    @Test
    public void sayHelloWithParam() {
        final Saying response = resouces.client().target("/hello-world?name=Leonardo").request().get(new GenericType<Saying>() {});
        assertThat(response.getContent()).isEqualTo("Hello, Leonardo!");
    }

}
