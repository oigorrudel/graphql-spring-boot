package br.xksoberbado.graphqlspringboot.resolver;

import br.xksoberbado.graphqlspringboot.input.PetInput;
import br.xksoberbado.graphqlspringboot.model.Person;
import br.xksoberbado.graphqlspringboot.model.Pet;
import br.xksoberbado.graphqlspringboot.repository.PersonRepository;
import br.xksoberbado.graphqlspringboot.repository.PetRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PetResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PersonRepository personRepository;

    public Collection<Pet> findAllPets(){
        return repository.findAll();
    }

    public Pet savePet(PetInput input){
        Person owner = personRepository.findById(input.getOwnerId()).get();
        return repository.save(new Pet(input.getName(), owner));
    }
}
