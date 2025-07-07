package com.example.inventory_service;

import com.example.inventory.InventoryGrpc;
import com.example.inventory.InventoryProto.InventoryRequest;
import com.example.inventory.InventoryProto.InventoryResponse;


import com.example.inventory_service.service.InventoryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
@RequiredArgsConstructor
public class InventoryGrpcServer extends InventoryGrpc.InventoryImplBase {

    private final InventoryService inventoryService;

    @Override
    public void checkInventory(InventoryRequest request, StreamObserver<InventoryResponse> responseObserver) {
        Mono<Boolean> availableMono = inventoryService.checkInventory(request.getProduct(), request.getQuantity());

        availableMono.subscribe(available -> {
            InventoryResponse response = InventoryResponse.newBuilder()
                    .setAvailable(available)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        });
    }
}
