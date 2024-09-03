package com.example.payment_platform.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "user_payment_methods", schema = "public")
public class UserPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name="provider")
    private Provider provider;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="billing_address")
    private String billingAddress;

    @Column(name="is_default")
    private boolean isDefault;

    @Column(name="created_at")
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    private OffsetDateTime updatedAt;
}
