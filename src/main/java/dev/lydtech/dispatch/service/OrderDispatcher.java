package dev.lydtech.dispatch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderDispatcher {

    public void dispatch(String order) {
        log.info("Order dispatched: {}", order);
    }
}
