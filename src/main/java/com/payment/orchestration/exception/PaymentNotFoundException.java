package com.payment.orchestration.exception;

public class PaymentNotFoundException
        extends RuntimeException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}