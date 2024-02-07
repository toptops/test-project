package com.example.kafkaexample.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(final String message) {
        sendMessage(null, message);
    }

    public void sendMessage(final String key, final String message) {
        final String topic = "hello.kafka";
        CompletableFuture<SendResult<String, String>> metadata = kafkaTemplate.send(topic, key, message);
        metadata.thenAccept(result -> {
            log.info("Message sent to topic: {}, partition: {}, offset: {}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Failed to send message: " + ex.getMessage());
            return null;
        });

    }
}
