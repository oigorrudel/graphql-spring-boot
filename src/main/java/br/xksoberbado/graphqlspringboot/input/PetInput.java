package br.xksoberbado.graphqlspringboot.input;

import graphql.schema.GraphQLInputType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetInput implements GraphQLInputType {
    @Override
    public String getName() {
        return name;
    }

    private String name;
    private Long ownerId;
}
