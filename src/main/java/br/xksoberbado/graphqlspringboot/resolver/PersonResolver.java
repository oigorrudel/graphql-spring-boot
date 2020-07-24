package br.xksoberbado.graphqlspringboot.resolver;

import br.xksoberbado.graphqlspringboot.input.PersonInput;
import br.xksoberbado.graphqlspringboot.model.Person;
import br.xksoberbado.graphqlspringboot.repository.PersonRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PersonResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PersonRepository repository;

    public Collection<Person> findAllPeople(){
        return repository.findAll();
    }

    public Person findPersonById(Long id){
        return repository.findById(id).get();
    }

    public Person savePerson(PersonInput input){
        return repository.save(new Person(input.getName(), input.getAge(), input.getGender()));
    }

    public Person updateAge(Long personId, Integer age){
        Person person = repository.findById(personId).get();
        person.setAge(age);
        return repository.save(person);
    }
}
