package com.gltknbtn.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gltknbtn.dto.InputOutputLogDto;
import com.gltknbtn.enums.LogDirection;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class LoggingProducer {
    private Logger logger = Logger.getLogger(LoggingProducer.class);
    private ObjectMapper objectMapper;
    private KafkaTemplate<Integer,String> kafkaTemplate;

    private String topic = "log-inout-events";

    @Autowired
    public LoggingProducer(ObjectMapper objectMapper,
                           KafkaTemplate<Integer,String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<Integer, String>> logInputOutput(Object inOutMsgObject, LogDirection logDirection, String correlationId, Integer status, Long executionTime) throws JsonProcessingException {

        InputOutputLogDto inputOutputLogDto = InputOutputLogDto.builder().message(objectMapper.writeValueAsString(inOutMsgObject))
                .correlationId(correlationId)
                .createdDate(LocalDateTime.now())
                .logDirection(logDirection)
                .responseStatus(LogDirection.RESPONSE.equals(logDirection) ? status : null)
                .executionTime(LogDirection.RESPONSE.equals(logDirection) ? executionTime : null)
                .build();

        int sampleKey = getRandomVal(1,100);
        logger.info("sampleKey: " + sampleKey);
        String reqJson = objectMapper.writeValueAsString(inputOutputLogDto);
        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(sampleKey, reqJson, topic);
        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(sampleKey, reqJson, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(sampleKey, reqJson, result);
            }
        });

        return listenableFuture;
    }

    private int getRandomVal(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(max - min) + min;
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        logger.error("Error Sending the Message and the exception is "+ ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            logger.error("Error in OnFailure: "+ throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        logger.info("Message Sent SuccessFully for the key : "+ key+ " and the value is "+value +" , partition is "+ result.getRecordMetadata().partition());
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String requestJson, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, requestJson, recordHeaders);
    }
}
