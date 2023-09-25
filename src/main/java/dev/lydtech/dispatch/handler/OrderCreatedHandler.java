package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.service.OrderDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedHandler {

    private final OrderDispatcher orderDispatcher;

    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer"
    )
    public void listen(String payload) {
        orderDispatcher.dispatch(payload);
        log.info("Order created: {}", payload);
    }
}
