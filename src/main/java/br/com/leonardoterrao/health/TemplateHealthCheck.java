package br.com.leonardoterrao.health;

import br.com.leonardoterrao.template.Template;
import com.codahale.metrics.health.HealthCheck;

import java.util.Optional;

public class TemplateHealthCheck extends HealthCheck {

    private final Template template;

    public TemplateHealthCheck(Template template) {
        this.template = template;
    }

    @Override
    protected Result check() {
        template.render(Optional.of("woo"));
        template.render(Optional.empty());
        return Result.healthy();
    }

}
