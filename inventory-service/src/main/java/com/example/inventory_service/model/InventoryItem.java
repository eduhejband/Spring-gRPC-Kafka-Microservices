package com.example.inventory_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("inventory")
public class InventoryItem {
    @Id
    private String id;
    private String product;
    private int stock;
}
