package br.com.leonardoterrao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class User implements Principal {

    private final String name;

    private final Set<String> roles;

    public int getId() {
        return (int) Math.random() * 100;
    }

}
