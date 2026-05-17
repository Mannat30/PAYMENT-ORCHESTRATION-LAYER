package com.payment.orchestration.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    @KafkaListener(
            topics = "payment-success",
            groupId = "payment-group"
    )
    public void consume(String message) {

        System.out.println(
                "Payment event received: " + message
        );

    }
}