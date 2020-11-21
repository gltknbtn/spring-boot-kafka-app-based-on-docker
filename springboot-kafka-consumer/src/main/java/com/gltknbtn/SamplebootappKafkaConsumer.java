package com.gltknbtn;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableDiscoveryClient
public class SamplebootappKafkaConsumer {
    private final static Logger LOG =  Logger.getLogger(SamplebootappKafkaConsumer.class);
    public static void main(String[] args) {
        SpringApplication.run(SamplebootappKafkaConsumer.class, args);
        LOG.info("Hello from Spring Boot");
    }
}

