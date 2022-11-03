package com.prototype.platform.api.kafka.producer.service;

import com.prototype.platform.api.kafka.DTO.Message;

public interface KafkaProducerService {

    public void sendMessage(String topic, Message message);
}
