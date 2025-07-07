package com.example.order_service.service;

import com.example.inventory.InventoryGrpc;
import com.example.inventory.InventoryProto.InventoryRequest;
import com.example.inventory.InventoryProto.InventoryResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
@Slf4j
public class InventoryGrpcClient {

    private final String grpcHost;
    private final int grpcPort;
    private InventoryGrpc.InventoryBlockingStub blockingStub;

    public InventoryGrpcClient(
            @Value("${inventory.grpc.host}") String grpcHost,
            @Value("${inventory.grpc.port}") int grpcPort
    ) {
        this.grpcHost = grpcHost;
        this.grpcPort = grpcPort;
        init();
    }

    private void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
        blockingStub = InventoryGrpc.newBlockingStub(channel);
    }

    public CompletableFuture<Boolean> checkInventory(String product, int quantity) {
        return CompletableFuture.supplyAsync(() -> {
            int attempts = 0;
            while (attempts < 5) {
                try {
                    InventoryRequest request = InventoryRequest.newBuilder()
                            .setProduct(product)
                            .setQuantity(quantity)
                            .build();
                    InventoryResponse response = blockingStub.checkInventory(request);
                    log.info("Inventory checked: {}", response.getAvailable());
                    return response.getAvailable();
                } catch (Exception ex) {
                    attempts++;
                    log.warn("Attempt {} failed to call gRPC: {}", attempts, ex.getMessage());
                    try {
                        Thread.sleep(2000); // espera 2s
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            throw new RuntimeException("Failed to connect to Inventory Service gRPC after retries");
        });
    }

}
