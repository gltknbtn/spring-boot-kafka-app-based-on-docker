package com.gltknbtn.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gltknbtn.service.LoggingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingEventsConsumer {

    @Autowired
    private LoggingService loggingService;

    @KafkaListener(topics = {"log-inout-events"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {

        log.info("ConsumerRecord : {} ", consumerRecord );
        loggingService.logInOut(consumerRecord);

    }
}
