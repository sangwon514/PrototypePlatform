package com.prototype.platform.api.kafka.consumer.component;

import com.prototype.platform.api.kafka.DTO.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageConsumer {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @KafkaListener(id = "test02", topics = "test02", groupId = "consumer-group-test02", containerFactory = "kafkaListenerContainerFactory")
    public void listenDomainEvent(@Headers MessageHeaders headers, @Payload Message message, Acknowledgment acknowledgment) {
        try {
            simpMessageSendingOperations.convertAndSend("/sub/chart/line", message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("consumer cause exception : " + e);
        }
    }

}
