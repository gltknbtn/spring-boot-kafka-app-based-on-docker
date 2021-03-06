package com.gltknbtn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gltknbtn.annotation.LogInOut;
import com.gltknbtn.request.SavePersonRequest;
import com.gltknbtn.service.CrmProxyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm")
public class CrmController {

    private final static Logger logger = Logger.getLogger(CrmController.class);

    @Autowired
    private CrmProxyService crmProxyService;

    @PostMapping(value = "savePerson")
    @LogInOut
    public ResponseEntity<Boolean> savePerson(@RequestBody SavePersonRequest savePersonRequest) throws JsonProcessingException {
        logger.info("savePersonRequest as json : " + savePersonRequest.toJson());
        crmProxyService.savePerson(savePersonRequest);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
    }

}