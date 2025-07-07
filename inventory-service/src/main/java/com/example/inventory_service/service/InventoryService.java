package com.example.inventory_service.service;

import com.example.inventory_service.model.InventoryItem;
import com.example.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Mono<Boolean> checkInventory(String product, int quantity) {
        return inventoryRepository.findByProduct(product)
                .map(item -> item.getStock() >= quantity)
                .defaultIfEmpty(false);
    }

    public Mono<InventoryItem> updateStock(String product, int quantity) {
        return inventoryRepository.findByProduct(product)
                .flatMap(item -> {
                    item.setStock(item.getStock() - quantity);
                    return inventoryRepository.save(item);
                });
    }
}
