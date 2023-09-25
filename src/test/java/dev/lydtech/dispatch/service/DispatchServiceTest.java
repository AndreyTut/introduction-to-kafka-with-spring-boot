package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

class DispatchServiceTest {

    private DispatchService service;
    private KafkaTemplate<String, Object> producerMock;

    @BeforeEach
    void setUp() {
        producerMock = Mockito.mock(KafkaTemplate.class);
        service = new DispatchService(producerMock);
    }

    @Test
    void process_ok() throws ExecutionException, InterruptedException {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        Mockito.when(producerMock.send(anyString(), any())).thenReturn(Mockito.mock(CompletableFuture.class));
        service.process(testEvent);
    }

    @Test
    void process_exception() throws ExecutionException, InterruptedException {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("test", null)).when(producerMock).send(anyString(), any());
        assertThrows(RuntimeException.class, () -> service.process(testEvent));
    }
}
