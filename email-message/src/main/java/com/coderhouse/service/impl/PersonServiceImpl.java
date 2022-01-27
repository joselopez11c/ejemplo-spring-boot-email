package com.coderhouse.service.impl;

import com.coderhouse.model.Person;
import com.coderhouse.repository.PersonRepository;
import com.coderhouse.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Person updateById(Person user, Long id) {
        user.setId(id);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Person findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Person> findAll() {
        var persons = new ArrayList<Person>();
        repository.findAll().forEach(persons::add);
        return persons;
    }
}
