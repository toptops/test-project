package com.example.kafkaexample.test;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> customProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "211.54.26.38:29092,211.54.26.38:29093,211.54.26.38:29094");
        configProps.put(ProducerConfig.ACKS_CONFIG, "-1");
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, "1");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, KafkaCustomPartitioner.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> customKafkaTemplate(ProducerFactory<String, String> customProducerFactory) {
        return new KafkaTemplate<>(customProducerFactory);
    }
}
