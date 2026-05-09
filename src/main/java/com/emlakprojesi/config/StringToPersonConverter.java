package com.emlakprojesi.config;

import com.emlakprojesi.domain.Person;
import com.emlakprojesi.repository.PersonRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPersonConverter implements Converter<String, Person> {

    private final PersonRepository personRepository;

    public StringToPersonConverter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return personRepository.findById(Long.valueOf(source)).orElse(null);
    }
}
