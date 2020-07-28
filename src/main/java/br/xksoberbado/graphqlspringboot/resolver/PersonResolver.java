package br.xksoberbado.graphqlspringboot.resolver;

import br.xksoberbado.graphqlspringboot.input.PersonInput;
import br.xksoberbado.graphqlspringboot.model.Person;
import br.xksoberbado.graphqlspringboot.repository.PersonRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersonResolver implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private PersonRepository repository;

    private ConcurrentHashMap<Long, FluxSink<Person>> personSubscribers = new ConcurrentHashMap<>();

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
        repository.save(person);

        if(personSubscribers.containsKey(personId))
            personSubscribers.get(personId).next(person);

        return person;
    }

    public Publisher<Person> onPersonUpdated(Long personId){
        return Flux.create(subscriber ->
                                personSubscribers.put(personId, subscriber.onDispose(() -> personSubscribers.remove(personId, subscriber))),
                            FluxSink.OverflowStrategy.LATEST);
    }

}
