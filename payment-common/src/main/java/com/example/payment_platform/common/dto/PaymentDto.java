package com.example.payment_platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String id;
    private String payerId;
    private String payeeId;
    private String paymentMethodId;
    private Double amount;
    private String currency;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


    public PaymentDto(String id, PaymentDto other) {
        this.id = id;
        this.payerId = other.payerId;
        this.payeeId = other.payeeId;
        paymentMethodId = other.paymentMethodId;
        this.amount = other.amount;
        this.currency = other.currency;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

}
