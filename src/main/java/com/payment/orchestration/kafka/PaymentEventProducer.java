package com.payment.orchestration.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentEventProducer(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentSuccessEvent(
            String message
    ) {

        kafkaTemplate.send(
                "payment-events",
                message
        );

        System.out.println(
                "Payment event published: " + message
        );
    }
}