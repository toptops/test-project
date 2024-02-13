package com.example.kafkaexample.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> customKafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "hello.kafka";

    public void sendMessage(final String message) {
        sendMessage(null, message);
    }

    public void sendMessage(final String key, final String message) {
        CompletableFuture<SendResult<String, String>> metadata = kafkaTemplate.send(TOPIC, key, message);
        metadata.thenAccept(result -> {
            log.info("Message sent to topic: {}, partition: {}, offset: {}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Failed to send message: " + ex.getMessage());
            return null;
        });
    }

    public void sendMessageByCustomPartitioner(final String key, final String message) {
        customKafkaTemplate.send(TOPIC, key, message);
    }

    public void sendMessageByCallBack(final String key, final String message) {
        kafkaTemplate.send(TOPIC, key, message).thenAcceptAsync(r -> System.out.println(r.toString()));
    }
}
