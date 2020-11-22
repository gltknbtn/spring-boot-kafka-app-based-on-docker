package com.gltknbtn.repository.mongo;

import com.gltknbtn.model.mongo.InputOutputLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface InputOutputLogMongoRepository extends MongoRepository<InputOutputLog, Long> {

    public List<InputOutputLog> findByCorrelationId(String correlationId);

}