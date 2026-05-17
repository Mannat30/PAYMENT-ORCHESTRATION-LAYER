package com.payment.orchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class PaymentOrchestrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                PaymentOrchestrationApplication.class,
                args
        );

    }
}