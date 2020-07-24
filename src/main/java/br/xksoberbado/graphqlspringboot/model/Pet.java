package br.xksoberbado.graphqlspringboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PETS")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;


    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person owner;

    public Pet(String name, Person owner) {
        this.name = name;
        this.owner = owner;
    }
}
