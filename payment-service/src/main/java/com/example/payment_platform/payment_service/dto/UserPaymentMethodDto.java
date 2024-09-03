package com.example.payment_platform.payment_service.dto;

import com.example.payment_platform.common.model.PaymentType;
import com.example.payment_platform.common.model.Provider;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserPaymentMethodDto {

    private UUID id;
    private PaymentType paymentType;
    private Provider provider;
    private String accountNumber;
    private LocalDate expirationDate;
    private boolean isDefault;
}
