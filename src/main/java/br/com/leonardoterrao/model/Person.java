package br.com.leonardoterrao.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@Entity
@Table(name = "people")
@NamedQueries({
        @NamedQuery(name = Person.QUERY_FIND_ALL, query = "SELECT p FROM Person p")
})
public class Person {

    public static final String QUERY_FIND_ALL = "br.com.leonardoterrao.model.Person.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "job", nullable = false, length = 100)
    private String job;

}
