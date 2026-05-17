package com.payment.orchestration.service;

import com.payment.orchestration.dto.CreatePaymentRequest;
import com.payment.orchestration.dto.PaymentResponse;
import com.payment.orchestration.entity.PaymentStatus;
import com.payment.orchestration.entity.PaymentTransaction;
import com.payment.orchestration.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;
import com.payment.orchestration.exception.PaymentNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import java.time.LocalDateTime;
import java.util.UUID;
import com.payment.orchestration.dto.UpdatePaymentStatusRequest;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import com.payment.orchestration.dto.UpdatePaymentStatusRequest;
import com.payment.orchestration.exception.PaymentNotFoundException;
import com.payment.orchestration.kafka.PaymentEventProducer;
import com.payment.orchestration.exception.PaymentNotFoundException;
import com.payment.orchestration.dto.UpdatePaymentStatusRequest;
import com.payment.orchestration.exception.PaymentNotFoundException;

@Service
public class PaymentService {

    private final PaymentTransactionRepository repository;
    private final PaymentEventProducer producer;
    public PaymentService(
            PaymentTransactionRepository repository,
            PaymentEventProducer producer
    ) {
        this.repository = repository;
        this.producer = producer;
    }


    public PaymentResponse createPayment(
            CreatePaymentRequest request
    ) {

        PaymentTransaction transaction = new PaymentTransaction();

        transaction.setTransactionId(UUID.randomUUID().toString());

        transaction.setOrderId(request.getOrderId());

        transaction.setAmount(request.getAmount());

        transaction.setPaymentMethod(request.getPaymentMethod());

        transaction.setStatus(PaymentStatus.PENDING);

        transaction.setCreatedAt(LocalDateTime.now());

        PaymentTransaction saved = repository.save(transaction);

        PaymentResponse response = new PaymentResponse();

        response.setTransactionId(saved.getTransactionId());

        response.setOrderId(saved.getOrderId());

        response.setAmount(saved.getAmount());

        response.setPaymentMethod(saved.getPaymentMethod());

        response.setStatus(saved.getStatus());

        return response;
    }
    @Cacheable(
            value = "payments",
            key = "#transactionId"
    )
    public PaymentResponse getPaymentByTransactionId(
            String transactionId
    ) {

        PaymentTransaction transaction =
                repository.findByTransactionId(transactionId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found with transaction id: "
                                                + transactionId
                                )
                        );

        PaymentResponse response = new PaymentResponse();

        response.setTransactionId(transaction.getTransactionId());

        response.setOrderId(transaction.getOrderId());

        response.setAmount(transaction.getAmount());

        response.setPaymentMethod(transaction.getPaymentMethod());

        response.setStatus(transaction.getStatus());

        return response;
    }
    @CacheEvict(
            value = "payments",
            key = "#transactionId"
    )
// request
//    )
    public PaymentResponse updatePaymentStatus(
            String transactionId,
            UpdatePaymentStatusRequest request
    ) {

        PaymentTransaction transaction =
                repository.findByTransactionId(transactionId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found"
                                )
                        );

        transaction.setStatus(request.getStatus());

        if (request.getStatus() == PaymentStatus.SUCCESS) {

            producer.publishPaymentSuccessEvent(
                    "Payment successful for transaction: "
                            + transactionId
            );
        }

        PaymentTransaction updated =
                repository.save(transaction);

        PaymentResponse response =
                new PaymentResponse(
                        updated.getTransactionId(),
                        updated.getOrderId(),
                        updated.getAmount(),
                        updated.getPaymentMethod(),
                        updated.getStatus()
                );

        return response;
    }

    public List<PaymentResponse> getAllPayments() {

        List<PaymentTransaction> transactions =
                repository.findAll();

        return transactions.stream()
                .map(transaction -> {

                    PaymentResponse response =
                            new PaymentResponse();

                    response.setTransactionId(
                            transaction.getTransactionId()
                    );

                    response.setOrderId(
                            transaction.getOrderId()
                    );

                    response.setAmount(
                            transaction.getAmount()
                    );

                    response.setPaymentMethod(
                            transaction.getPaymentMethod()
                    );

                    response.setStatus(
                            transaction.getStatus()
                    );

                    return response;
                })
                .toList();
    }
    public String deletePayment(
            String transactionId
    ) {

        PaymentTransaction transaction =
                repository.findByTransactionId(transactionId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found with transaction id: "
                                                + transactionId
                                )
                        );

        repository.delete(transaction);

        return "Payment deleted successfully";
    }
}