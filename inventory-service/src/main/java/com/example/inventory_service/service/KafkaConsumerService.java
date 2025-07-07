package com.example.inventory_service.service;

import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-created-topic", groupId = "inventory-group")
    public void consumeOrderCreated(String message) {
        log.info("Received Kafka message: {}", message);
    }
}
