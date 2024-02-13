package com.example.kafkaexample.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * 음.. 사용되는 함수를 못찾겠다.
 */
@Slf4j
public class KafkaAsyncCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        if(e != null) {
            log.error("producer error metadata: {}, error message: {}", metadata.toString(), e.getMessage());
        } else {
            log.info("producer callback test metadata: {}", metadata.toString());
        }
    }
}
