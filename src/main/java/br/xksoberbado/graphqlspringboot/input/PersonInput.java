package br.xksoberbado.graphqlspringboot.input;

import br.xksoberbado.graphqlspringboot.model.Gender;
import graphql.schema.GraphQLInputType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInput implements GraphQLInputType {
    @Override
    public String getName() {
        return name;
    }

    private String name;
    private Integer age;
    private Gender gender;
}
