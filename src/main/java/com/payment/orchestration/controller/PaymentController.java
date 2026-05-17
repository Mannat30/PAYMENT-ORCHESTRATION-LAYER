package com.payment.orchestration.controller;

import com.payment.orchestration.dto.CreatePaymentRequest;
import com.payment.orchestration.dto.PaymentResponse;
import com.payment.orchestration.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.payment.orchestration.dto.UpdatePaymentStatusRequest;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {


        return paymentService.createPayment(request);
    }
    @GetMapping("/{transactionId}")
    public PaymentResponse getPaymentByTransactionId(
            @PathVariable String transactionId
    ) {

        return paymentService
                .getPaymentByTransactionId(transactionId);
    }
    @GetMapping
    public List<PaymentResponse> getAllPayments() {

        return paymentService.getAllPayments();
    }
    @PatchMapping("/{transactionId}/status")
    public PaymentResponse updatePaymentStatus(

            @PathVariable String transactionId,

            @RequestBody UpdatePaymentStatusRequest request
    ) {

        return paymentService.updatePaymentStatus(
                transactionId,
                request
        );
    }
    @DeleteMapping("/{transactionId}")
    public String deletePayment(
            @PathVariable String transactionId
    ) {

        return paymentService.deletePayment(
                transactionId
        );
    }
}