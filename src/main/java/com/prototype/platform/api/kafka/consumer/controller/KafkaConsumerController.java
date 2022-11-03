package com.prototype.platform.api.kafka.consumer.controller;

import com.prototype.platform.api.kafka.consumer.service.impl.KafkaMessageConsumerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class KafkaConsumerController {

    private final KafkaMessageConsumerImpl messageConsumer;

    @PostMapping("/start/{id}")
    public void consumerStart(@RequestParam String id) {
        messageConsumer.consumerStart(id);
    }

    @PostMapping("/stop/{id}")
    public void consumerStop(@RequestParam String id) {
        messageConsumer.consumerStop(id);
    }

    @PostMapping("/start")
    public void consumerAllstart() {
        messageConsumer.consumerAllStart();
    }

    @PostMapping("/stop")
    public void consumerAllStop() {
        messageConsumer.consumerAllStop();
    }
}
