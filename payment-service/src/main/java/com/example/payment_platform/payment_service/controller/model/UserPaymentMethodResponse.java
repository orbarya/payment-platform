package com.example.payment_platform.payment_service.controller.model;

import com.example.payment_platform.common.model.PaymentType;
import com.example.payment_platform.common.model.Provider;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserPaymentMethodResponse {
    private UUID id;
    private PaymentType paymentType;
    private Provider provider;
    private String accountNumber;
    private boolean isDefault;
}
