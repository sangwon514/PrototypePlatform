package com.prototype.platform.api.kafka.consumer.service.impl;

import com.prototype.platform.api.kafka.consumer.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageConsumerImpl implements KafkaConsumerService {

    private final KafkaListenerEndpointRegistry registry;

    public void consumerStart(String id) {
        registry.getListenerContainer(id).start();
    }

    public void consumerStop(String id) {
        registry.getListenerContainer(id).stop();
    }

    public void consumerAllStart() {
        registry.getListenerContainers().forEach(MessageListenerContainer::start);
    }

    public void consumerAllStop() {
        registry.getListenerContainers().forEach(MessageListenerContainer::stop);
    }
}
