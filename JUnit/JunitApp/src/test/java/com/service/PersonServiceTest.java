package com.service;

import com.exception.InvalidPersonException;
import com.model.Person;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {
    PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
        System.out.println("Person Service Initializing...");
    }

    @AfterEach
    public void finish(){
        personService =null;
        System.out.println("Person Service de-structuring...");
    }

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

        //object to validate age, when it is true
        Person p1 = new Person(1,"Sammy",16,"Liverpool");
        InvalidPersonException e2 = Assertions.assertThrows(InvalidPersonException.class,
                ()->personService.validatePerson(p1));

        Assertions.assertEquals("You are underage for this op",e2.getMessage());

        //when it is false
        Person p2 = new Person(2,"Darren",25,"Manchester");
        Assertions.assertDoesNotThrow(()->personService.validatePerson(p2));
    }

    @Test
    public void validateRegister(){
        List<Person> list = personService.registerPersons();
        List<Person> list2 = personService.getAdultPersons();

        //check if there are 2 adults
        Assertions.assertEquals(2,
                list2.stream().filter(person -> person.getAge()>=18).count());

        //assert if there is any minor
        Assertions.assertTrue(list.stream().anyMatch(person -> person.getAge()<18),"There should not be minor in list");

        //assert if any one lives in Mumbai
        Assertions.assertFalse(list.stream()
                .anyMatch(person ->
                        person.getCity().equalsIgnoreCase("Mumbai")),"No person lives in Mumbai.");
    }
}