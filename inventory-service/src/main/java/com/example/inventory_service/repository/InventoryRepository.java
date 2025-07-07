package com.example.inventory_service.repository;

import com.example.inventory_service.model.InventoryItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InventoryRepository extends ReactiveMongoRepository<InventoryItem, String> {
    Mono<InventoryItem> findByProduct(String product);
}
