package com.prototype.platform.api.kafka.producer.controller;

import com.prototype.platform.api.kafka.DTO.Message;
import com.prototype.platform.api.kafka.producer.service.impl.KafkaMessageProducerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class KafkaProducerController {

    public final KafkaMessageProducerImpl messageProducer;

    @PostMapping("/send-message/{topic}")
    public void sendMessage(@PathVariable("topic") String topic, @RequestBody Message message) {
        messageProducer.sendMessage(topic, message);
    }

}
