package com.secor.payment;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderLookupService {

    private final WebClient webClient;

    public OrderLookupService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8090").build();
    }

    public OrderProcess fetchOrderDetails(Long orderId) {
        return webClient.get()
                .uri("/api/orders/get/{id}", orderId)
                .retrieve()
                .bodyToMono(OrderProcess.class)
                .block(); // Use block for synchronous calls (optional)
    }
}
