package com.franchise.management.batch.processor;

import com.franchise.management.batch.dto.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person,Person> {

    @Override
    public Person process(final Person person){
        String firstname = person.getFirstname();
        String lastName = person.getLastName();

        return person;
    }
}
