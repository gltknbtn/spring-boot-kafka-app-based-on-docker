package com.gltknbtn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gltknbtn.model.solr.InputOutputLog;
import com.gltknbtn.repository.mongo.InputOutputLogMongoRepository;
import com.gltknbtn.repository.mysql.InputOutputLogMysqlRepository;
import com.gltknbtn.repository.solr.InputOutputLogSolrRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggingService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    private InputOutputLogMysqlRepository inputOutputLogMysqlRepository;
    @Autowired
    private InputOutputLogMongoRepository inputOutputLogMongoRepository;
    @Autowired
    private InputOutputLogSolrRepository inputOutputLogSolrRepository;

    public void logInOut(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("inputOutputLog as consumerRecord : {} ", consumerRecord);

        saveInputOutputLog4Mongo(consumerRecord);

        saveInputOutputLog4Mysql(consumerRecord);

        saveInputOutputLog4Solr(consumerRecord);
    }

    private void saveInputOutputLog4Mongo(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        com.gltknbtn.model.mongo.InputOutputLog inputOutputLogMongo = objectMapper.readValue(consumerRecord.value(), com.gltknbtn.model.mongo.InputOutputLog.class);
        inputOutputLogMongoRepository.save(inputOutputLogMongo);
    }

    private void saveInputOutputLog4Mysql(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        com.gltknbtn.model.mysql.InputOutputLog inputOutputLogMysql = objectMapper.readValue(consumerRecord.value(), com.gltknbtn.model.mysql.InputOutputLog.class);
        inputOutputLogMysqlRepository.save(inputOutputLogMysql);
    }

    private void saveInputOutputLog4Solr(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        InputOutputLog inputOutputLogSolr = objectMapper.readValue(consumerRecord.value(), InputOutputLog.class);
        inputOutputLogSolr.setId("id_"+System.currentTimeMillis());
        inputOutputLogSolrRepository.save(inputOutputLogSolr);
    }

    public void handleRecovery(ConsumerRecord<Integer,String> record){

        /*
        Integer key = record.key();
        String message = record.value();

        ListenableFuture<SendResult<Integer,String>> listenableFuture = kafkaTemplate.sendDefault(key, message);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, message, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, message, result);
            }
        });

         */
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        /*
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }

         */
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
