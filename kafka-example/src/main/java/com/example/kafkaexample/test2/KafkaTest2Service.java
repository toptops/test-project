package com.example.kafkaexample.test2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaTest2Service {

    @KafkaListener(topics = "test", groupId = "test-group-00")
    public void recordListener(ConsumerRecord<String, String> record) {
        log.info("# test consumer v1 record: {}", record.toString());
    }

    @KafkaListener(topics = "test", groupId = "test-group-01")
    public void singleTopicListener(String messageValue) {
        log.info("# test consumer v2 message(String): {}", messageValue);
    }

    @KafkaListener(topics = "test",
            groupId = "test-group-02",
            properties = {
                "max.poll.interval.ms:20000",
                "auto.offset.reset:earliest"
            })
    public void singleTopicWithPropertiesListener(String messageValue) {
        log.info("# test consumer v3 message(String): {}", messageValue);
    }

    @KafkaListener(topics = "test", groupId = "test-group-03", concurrency = "3")
    public void concurrentTopicListener(String messageValue) {
        log.info("# test consumer v4 message(String): {}", messageValue);
    }

    @KafkaListener(topicPartitions = {
                @TopicPartition(topic = "test01", partitions = {"0", "1"}),
                @TopicPartition(topic = "test02", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "3"))
            })
    public void listenerSpecificPartition(ConsumerRecord<String, String> record) {
        log.info("# test consumer v5 message: {}", record.toString());
    }
}
