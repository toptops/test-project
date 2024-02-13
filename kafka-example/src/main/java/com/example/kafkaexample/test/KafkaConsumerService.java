package com.example.kafkaexample.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "hello.kafka", groupId = "my-consumer-group", id = "test1")
    public void consumerPartition1(String message) {
        log.info("# consumer1 test message: {}", message);
    }

    @KafkaListener(topics = "hello.kafka", groupId = "my-consumer-group", id = "test2")
    public void consumerPartition2(String message) {
        log.info("# consumer2 test message: {}", message);
    }
}
