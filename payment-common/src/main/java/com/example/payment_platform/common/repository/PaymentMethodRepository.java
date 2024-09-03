package com.example.payment_platform.common.repository;

import com.example.payment_platform.common.model.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<UserPaymentMethod, UUID> {

    List<UserPaymentMethod> findByUserId(UUID userId);

    // Check if a PaymentMethod exists for a specific userId and paymentMethodId
    boolean existsByIdAndUser_Id(UUID paymentMethodId, UUID userId);

}
