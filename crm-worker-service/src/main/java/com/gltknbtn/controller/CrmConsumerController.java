package com.gltknbtn.controller;

import com.gltknbtn.request.SavePersonRequest;
import com.gltknbtn.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrmConsumerController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "savePerson")
    public void savePerson(@RequestBody SavePersonRequest savePersonRequest) {
        personService.savePerson(savePersonRequest);
    }
}
