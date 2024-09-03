package com.example.payment_platform.payment_service.controller.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PayeeResponse {
    private UUID id;
    private String email;
    private String fullName;
}
