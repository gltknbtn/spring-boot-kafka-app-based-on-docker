package com.gltknbtn.service;

import com.gltknbtn.model.mysql.Person;
import com.gltknbtn.repository.mysql.PersonRepository;
import com.gltknbtn.request.SavePersonRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrmService {

    private Logger logger = Logger.getLogger(CrmService.class);

    private PersonRepository personRepository;

    @Autowired
    public CrmService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public void savePerson(SavePersonRequest savePersonRequest) {
        logger.info("savePerson started for "+ savePersonRequest.getName());
        Person person = Person.builder().name(savePersonRequest.getName() + " " + savePersonRequest.getSurname()).build();
        personRepository.save(person);
        logger.info(savePersonRequest.getName() + " saved successfully.");
    }
}
