package com.example.order_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderCreatedEvent(String message) {
        kafkaTemplate.send("order-created-topic", message);
    }
}
