package com.gltknbtn.service;

import com.gltknbtn.model.mysql.Person;
import com.gltknbtn.repository.mysql.PersonRepository;
import com.gltknbtn.request.SavePersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired private PersonRepository personRepository;
    public void savePerson(SavePersonRequest savePersonRequest) {
        Person person = new Person();
        person.setName(savePersonRequest.getName());
        personRepository.save(person);
    }
}
