package com.service;

import com.model.Person;

import java.util.List;

public class PersonService {
    public int countPerson(List<Person> list){
        if(list==null){
            throw new RuntimeException("List cannot be null");
        }
        return list.size();
    }
}