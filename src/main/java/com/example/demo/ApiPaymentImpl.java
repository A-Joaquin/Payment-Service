package com.example.demo;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApiPaymentImpl implements ApiPayment {

    @Override
    public boolean processPayment(PaymentRequestDto paymentRequestDto) {
        // Verificar que la tarjeta no esté vencida
        if (paymentRequestDto.expirationDate().isBefore(LocalDate.now())) {
            return false; // Tarjeta expirada
        }
        // Verificar el formato del número de tarjeta y el CVV
        if (paymentRequestDto.cardNumber().length() != 16 || paymentRequestDto.cvv().length() != 3) {
            return false; // Tarjeta o CVV inválidos
        }
        // Verificar que el monto sea mayor que cero
        if (paymentRequestDto.amount() <= 0) {
            return false; // Monto no válido
        }
        // Simulamos que el pago fue exitoso si pasa todas las verificaciones
        return true;
    }
}
