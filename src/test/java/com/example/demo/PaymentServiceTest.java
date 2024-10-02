package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private ApiPayment apiPayment;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        apiPayment = Mockito.mock(ApiPayment.class);
        paymentService = new PaymentService(apiPayment);
    }

    @Test
    void processPaymentSuccess() {
        PaymentRequestDto requestDto = new PaymentRequestDto("1234567812345678", "123", LocalDate.now().plusYears(1), 100.0);
        when(apiPayment.processPayment(requestDto)).thenReturn(true);

        PaymentResponseDto response = paymentService.processPayment(requestDto);
        assertTrue(response.success());
        assertEquals("Payment processed successfully.", response.message());
        verify(apiPayment).processPayment(requestDto);
    }

    @Test
    void processPaymentFailure() {
        PaymentRequestDto requestDto = new PaymentRequestDto("1234567812345678", "123", LocalDate.now().minusYears(1), 100.0);
        when(apiPayment.processPayment(requestDto)).thenReturn(false);

        PaymentResponseDto response = paymentService.processPayment(requestDto);
        assertFalse(response.success());
        assertEquals("Payment failed. Check card details or amount.", response.message());
        verify(apiPayment).processPayment(requestDto);
    }
}
