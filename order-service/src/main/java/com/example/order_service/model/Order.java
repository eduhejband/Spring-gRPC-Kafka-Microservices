package com.example.order_service.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
@Data
public class Order {

    @Id
    private Long id;
    private String product;
    private int quantity;
    private String status;

}
