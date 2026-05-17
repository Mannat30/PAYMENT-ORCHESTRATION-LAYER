package com.payment.orchestration.dto;

import com.payment.orchestration.entity.PaymentStatus;

public class PaymentResponse {

    private String transactionId;

    private String orderId;

    private Double amount;

    private String paymentMethod;

    private PaymentStatus status;

    public PaymentResponse() {
    }

    public PaymentResponse(
            String transactionId,
            String orderId,
            Double amount,
            String paymentMethod,
            PaymentStatus status
    ) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}