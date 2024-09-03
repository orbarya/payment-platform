package com.example.payment_platform.payment_service.controller.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreatePaymentResponse {

    private String id;
    private String payerId;
    private String payeeId;
    private String paymentMethodId;
    private Double amount;
    private String currency;
}
