package com.example.payment_platform.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="password_hash")
    private String passwordHash;

    @Column(name="full_name")
    private String fullName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="created_at")
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    private OffsetDateTime updatedAt;
}
