package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.service.OrderDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler subject;
    private OrderDispatcher orderDispatcher;
    @BeforeEach
    void setUp() {
        orderDispatcher = Mockito.mock(OrderDispatcher.class);
        subject = new OrderCreatedHandler(orderDispatcher);
    }


    @Test
    void listen() {
        subject.listen("test");
        Mockito.verify(orderDispatcher).dispatch("test");
    }
}