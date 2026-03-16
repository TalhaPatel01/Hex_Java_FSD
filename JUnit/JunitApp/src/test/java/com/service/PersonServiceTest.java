package com.service;

import com.exception.InvalidPersonException;
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

    @Test
    public void testValidatePerson(){
        //checking for null
        NullPointerException e = Assertions.assertThrows(NullPointerException.class,
                                ()->personService.validatePerson(null));

        //checking message for null
        Assertions.assertEquals("Person cannot be null",e.getMessage());

        //object to test 1 char
        Person p = new Person(1,"a",21,"London");
        InvalidPersonException e1 = Assertions.assertThrows(InvalidPersonException.class,
                                    ()->personService.validatePerson(p));

        Assertions.assertEquals("Person length should be more than 1",e1.getMessage());

        //object to validate age
        Person p1 = new Person(1,"Sammy",16,"Liverpool");
        InvalidPersonException e2 = Assertions.assertThrows(InvalidPersonException.class,
                ()->personService.validatePerson(p1));

        Assertions.assertEquals("You are underage for this op",e2.getMessage());
    }
}