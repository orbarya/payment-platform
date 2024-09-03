package com.example.payment_platform.payment_service.controller.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CreatePaymentRequest {
    @NotNull
    @UUID
    private final String payerId;

    @NotNull
    @UUID
    private final String payeeId;

    @NotNull
    @UUID
    private final String paymentMethodId;

    @NotNull
    @Positive
    private final Double amount;

    @NotNull
    @Size(min = 3, max = 3, message = "The currency field must be of length 3")
    private final String currency;

}


