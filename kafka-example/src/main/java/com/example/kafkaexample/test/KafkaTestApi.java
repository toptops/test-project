package com.example.kafkaexample.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/kafka/test")
@RequiredArgsConstructor
public class KafkaTestApi {
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/{message}")
    public void test(@PathVariable String message) {
        log.info("# Kafka producer test, message: {}", message);
        kafkaProducerService.sendMessage(message);
    }

    @GetMapping("/{key}/{message}")
    public void test(@PathVariable String key, @PathVariable String message) {
        log.info("# kafka producer test, key: {}, message: {}", key, message);
        kafkaProducerService.sendMessage(key, message);
    }
}
