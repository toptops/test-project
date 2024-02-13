package com.example.kafkaexample.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/v1/kafka/test")
@RequiredArgsConstructor
public class KafkaTestApi {
    private final KafkaProducerService kafkaProducerService;
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

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

    @GetMapping("/custom/{key}/{message}")
    public void customTest(@PathVariable String key, @PathVariable String message) {
        log.info("# kafka custom partitioner producer test key: {}, message: {}", key, message);
        kafkaProducerService.sendMessageByCustomPartitioner(key, message);
    }

    @GetMapping("/pause")
    public void kafkaPause() {
        Collection<MessageListenerContainer> listenerContainers = kafkaListenerEndpointRegistry.getAllListenerContainers();
        for(MessageListenerContainer container : listenerContainers) {
            log.info("# consumer listener id: {}", container.getListenerId());
            if(!container.isRunning()) {
                log.info("# consumer is not running");
            } else if(container.isContainerPaused()) {
                log.info("# consumer is already paused");
            } else if(container.isPauseRequested()) {
                log.info("# consumer is already request to ba paused");
            } else {
                log.info("# pausing consumer: {}", container.getGroupId());
            }
            container.pause();
        }
    }

    @GetMapping("/pause/{id}")
    public void pauseKafkaByListenerId(@PathVariable String id) {
        kafkaListenerEndpointRegistry.getListenerContainer(id).pause();
    }
}
