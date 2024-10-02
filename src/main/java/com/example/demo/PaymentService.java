package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final ApiPayment apiPayment;

    public PaymentService(ApiPayment apiPayment) {
        this.apiPayment = apiPayment;
    }

    public PaymentResponseDto processPayment(PaymentRequestDto requestDto) {
        boolean success = apiPayment.processPayment(requestDto);
        if (success) {
            return new PaymentResponseDto(true, "Payment processed successfully.");
        } else {
            return new PaymentResponseDto(false, "Payment failed. Check card details or amount.");
        }
    }
}
