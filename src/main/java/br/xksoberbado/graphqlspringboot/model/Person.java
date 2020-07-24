package br.xksoberbado.graphqlspringboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PEOPLE")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "owner")
    private Collection<Pet> pets;


    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
