package com.example.order_service.controller;

import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;
import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();

    }

    @GetMapping("/rxjava")
    public Flowable<String> rxExample() {
        return Flowable.just("RxJava funcionando!", "Outro valor Rx");
    }

    @GetMapping("/virtual-thread")
    public CompletableFuture<String> virtualThreadEndpoint() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // simula I/O
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Executado em Virtual Thread: " + Thread.currentThread();
        });
    }
}
