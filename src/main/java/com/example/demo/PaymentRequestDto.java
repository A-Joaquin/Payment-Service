package com.example.demo;

import java.time.LocalDate;

public record PaymentRequestDto(String cardNumber, String cvv, LocalDate expirationDate, Double amount) {
}
