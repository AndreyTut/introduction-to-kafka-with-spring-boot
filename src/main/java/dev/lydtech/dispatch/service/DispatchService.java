package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String ORDER_DISPATCHED = "order.dispatched";
    private final KafkaTemplate<String, Object> producer;
    public void process(OrderCreated orderCreated) throws ExecutionException, InterruptedException {
        SendResult<String, Object> stringObjectSendResult = producer.send(
                ORDER_DISPATCHED,
                OrderDispatched.builder()
                        .orderId(orderCreated.getOrderId())
                        .build()
        ).get();
        System.out.println(stringObjectSendResult);
    }
}
