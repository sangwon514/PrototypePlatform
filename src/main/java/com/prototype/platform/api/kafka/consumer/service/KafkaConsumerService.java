package com.prototype.platform.api.kafka.consumer.service;

public interface KafkaConsumerService {

    public void consumerStart(String id);

    public void consumerStop(String id);

    public void consumerAllStart();

    public void consumerAllStop();
}
