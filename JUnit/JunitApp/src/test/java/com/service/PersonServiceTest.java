package com.service;

import com.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {
    PersonService personService = new PersonService();

    @Test
    public void testPerson() {
        // have 2 objects
        List<Person> list = new ArrayList<>();
        Person p1 = new Person(1, "Darren", 23, "Surrey");
        Person p2 = new Person(2, "Micheal", 24, "London");
        list.add(p1);
        list.add(p2);

        //have 1 object
        List<Person> list2 = new ArrayList<>();
        Person p3 = new Person(3, "Danny", 32, "Preston");
        list2.add(p3);

        Assertions.assertEquals(2, personService.countPerson(list));
        Assertions.assertEquals(1, personService.countPerson(list2));

        //null object, need to use try catch block
        List<Person> list3 = null;
        try {
            Assertions.assertEquals(0, personService.countPerson(list3));
        }
        catch(RuntimeException e){
            Assertions.assertEquals("List cannot be null",e.getMessage());
        }
    }
}