package br.xksoberbado.graphqlspringboot.repository;

import br.xksoberbado.graphqlspringboot.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
