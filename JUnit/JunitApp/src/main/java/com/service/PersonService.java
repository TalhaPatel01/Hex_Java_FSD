package com.service;

import com.exception.InvalidPersonException;
import com.model.Person;

import java.util.List;

public class PersonService {
    List<Person> list;

    {
        list = registerPersons();
    }

    public int countPerson(List<Person> list){
        if(list==null){
            throw new RuntimeException("List cannot be null");
        }
        return list.size();
    }

    public void validatePerson(Person person) {
        if(person==null){
            throw new NullPointerException("Person cannot be null");
        }
        if(person.getName().length()<2){
            throw new InvalidPersonException("Person length should be more than 1");
        }
        if(person.getAge()<18){
            throw new InvalidPersonException("You are underage for this op");
        }
    }

    public List<Person> registerPersons(){
        Person p1 = new Person(1,"Danny",23,"Surrey");
        Person p2 = new Person(2,"Micheal",21,"Birmingham");
        Person p3 = new Person(3,"Dani",16,"Madrid");
        return List.of(p1,p2,p3);
    }

    public List<Person> getAdultPersons(){
        return list.stream().filter(person->person.getAge()>18).toList();
    }
}