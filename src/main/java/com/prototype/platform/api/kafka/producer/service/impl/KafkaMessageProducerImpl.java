package com.prototype.platform.api.kafka.producer.service.impl;

import com.prototype.platform.api.kafka.DTO.Message;
import com.prototype.platform.api.kafka.producer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageProducerImpl implements KafkaProducerService {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

}
