package com.service;

import com.exception.InvalidPersonException;
import com.model.Person;

import java.util.List;

public class PersonService {
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
}