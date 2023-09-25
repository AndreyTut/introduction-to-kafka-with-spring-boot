package dev.lydtech.dispatch.utill;

import dev.lydtech.dispatch.message.OrderCreated;

public class TestUtills {
    public static OrderCreated createOrderCreated() {
        return OrderCreated.builder()
                .orderId(java.util.UUID.randomUUID())
                .message("test")
                .build();
    }
}
