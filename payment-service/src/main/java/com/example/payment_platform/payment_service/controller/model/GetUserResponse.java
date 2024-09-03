package com.example.payment_platform.payment_service.controller.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetUserResponse {
    private UUID id;
    private String userName;
    private String email;
    private String fullName;
    private String phoneNumber;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
