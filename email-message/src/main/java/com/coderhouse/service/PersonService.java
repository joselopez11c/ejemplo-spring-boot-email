package com.coderhouse.service;

import com.coderhouse.model.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);
    Person findById(Long id);
    Person updateById(Person person, Long id);
    void delete(Long id);
    Person findByName(String name);
    List<Person> findAll();

}
