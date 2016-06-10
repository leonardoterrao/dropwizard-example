package br.com.leonardoterrao;

import br.com.leonardoterrao.configuration.HelloWorldConfiguration;
import br.com.leonardoterrao.dao.PersonDAO;
import br.com.leonardoterrao.health.TemplateHealthCheck;
import br.com.leonardoterrao.model.Person;
import br.com.leonardoterrao.resources.HelloWorldResource;
import br.com.leonardoterrao.resources.PeopleResource;
import br.com.leonardoterrao.resources.PersonResource;
import br.com.leonardoterrao.view.PersonView;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String... args) throws Exception {
        new HelloWorldApplication().run(new String[] {"server", "configuration.yml"});
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
        new HibernateBundle<HelloWorldConfiguration>(Person.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
        final String template = configuration.getTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(new HelloWorldResource(template, configuration.getDefaultName()));
        environment.jersey().register(new PeopleResource(dao));
        environment.jersey().register(new PersonResource(dao));
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false))
        );
    }

}
