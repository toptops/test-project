package com.example.kafkaexample.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class CustomConsumerRebalanceListener implements ConsumerAwareRebalanceListener {

    @Override
    public void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsAssigned(consumer, partitions);
        log.info("#onPartitionsAssigned kafka rebalance test consumer info: {}, partition info: {}", consumer, partitions);
    }

    @Override
    public void onPartitionsRevokedAfterCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsRevokedAfterCommit(consumer, partitions);
        log.info("#onPartitionsRevokedAfterCommit kafka rebalance test consumer info: {}, partition info: {}", consumer, partitions);
    }

    @Override
    public void onPartitionsLost(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsLost(consumer, partitions);
        log.info("#onPartitionsLost kafka rebalance test consumer info: {}, partition info: {}", consumer, partitions);
    }
}
