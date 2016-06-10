package br.com.leonardoterrao.view;

import br.com.leonardoterrao.model.Person;
import io.dropwizard.views.View;

public class PersonView extends View {

    private final Person person;

    public PersonView(Template template, Person person) {
        super(template.getTemplateName());
        this.person = person;
    }

    public enum Template {
        FREEMARKER("freemarker/person.ftl"),
        MUSTACHE("mustache/person.mustache");

        private String templateName;

        Template(String templateName) {
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }

    public Person getPerson() {
        return person;
    }

}
