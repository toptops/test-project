package com.example.kafkaexample.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "hello.kafka", groupId = "my-consumer-group")
    public void consumerTest(String message) {
        log.info("# consumer test message: {}", message);
    }
}
