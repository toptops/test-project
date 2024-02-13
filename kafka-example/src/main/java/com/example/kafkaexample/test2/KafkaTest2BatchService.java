package com.example.kafkaexample.test2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaTest2BatchService {

    @KafkaListener(topics = "test", groupId = "test-group-06", containerFactory = "customContainerFactory")
    public void commitListener(ConsumerRecords<String, String> records, Acknowledgment ack) {
        records.forEach(r -> log.info("# batch consumer1 record: {}", r.toString()));
        ack.acknowledge();
    }

    @KafkaListener(topics = "test", groupId = "test-group-07", containerFactory = "customContainerFactory")
    public void consumerCommitListener(ConsumerRecords<String, String> records, Consumer<String, String> consumer) {
        records.forEach(r -> log.info("# batch consumer2 record: {}", r.toString()));
        consumer.commitAsync();
    }
}
