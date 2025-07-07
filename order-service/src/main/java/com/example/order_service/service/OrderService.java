package com.example.order_service.service;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;
    private final InventoryGrpcClient inventoryGrpcClient;

    public Mono<Order> createOrder(Order order) {
        order.setStatus("PENDING");

       return orderRepository.save(order)
                .flatMap(savedOrder -> {
                    kafkaProducerService.sendOrderCreatedEvent("Order ID " + savedOrder.getId() + " created");

                    return Mono.fromFuture(
                            inventoryGrpcClient.checkInventory(savedOrder.getProduct(), savedOrder.getQuantity())
                    ).map(available -> {
                        savedOrder.setStatus(available ? "CONFIRMED" : "FAILED");
                        return savedOrder;
                    });
                }).flatMap(orderRepository::save);
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();

    }
}
