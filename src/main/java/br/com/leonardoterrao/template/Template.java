package br.com.leonardoterrao.template;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@AllArgsConstructor
@ToString
public class Template {

    private final String content;
    private final String defaultName;

    public String render(Optional<String> name) {
        return String.format(content, name.orElse(defaultName));
    }

}
