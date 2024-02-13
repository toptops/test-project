package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    @KafkaListener(topics = "hello.kafka", groupId = "my-consumer-group")
    public void consumerPartition1(String message) {
        log.info("# another consumer1 test message: {}", message);
    }
}