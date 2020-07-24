package br.xksoberbado.graphqlspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.servlet.Filter;

@Configuration
public class LazyFilter {

    @Bean
    public Filter openSessionInView(){
        return new OpenEntityManagerInViewFilter();
    }

}
