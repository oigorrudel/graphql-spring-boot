package br.xksoberbado.graphqlspringboot.repository;

import br.xksoberbado.graphqlspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
