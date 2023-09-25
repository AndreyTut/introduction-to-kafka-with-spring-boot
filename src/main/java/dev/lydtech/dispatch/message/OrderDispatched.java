package dev.lydtech.dispatch.message;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderDispatched {
    private UUID orderId;
}
