package com.example.kafkaexample.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

@Slf4j
public class KafkaCustomPartitioner implements Partitioner {

    /**
     * 커스텀으로 사용할꺼면 알고리즘을 잘짜야겠다.. 특정 파티션으로 집중되는 경향이 있다.
     * 애매할꺼 같으면 그냥 라운드로빈으로 하는게 나을꺼 같다.
     *
     * @param topic The topic name
     * @param key The key to partition on (or null if no key)
     * @param keyBytes The serialized key to partition on( or null if no key)
     * @param value The value to partition on or null
     * @param valueBytes The serialized value to partition on or null
     * @param cluster The current cluster metadata
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(keyBytes == null) {
            throw new InvalidRecordException("Need Message Key");
        }

        if(key.equals("Test")) {
            return 0;
        }
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        log.info("# kafka partition num: {}", Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions);
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    @Override
    public void close() {
        log.info("# Kafka close");
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }
}
