package com.payment.orchestration.dto;

import com.payment.orchestration.entity.PaymentStatus;

public class UpdatePaymentStatusRequest {

    private PaymentStatus status;

    public UpdatePaymentStatusRequest() {
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}