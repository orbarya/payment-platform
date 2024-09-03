package com.example.payment_platform.common.model;

public enum PaymentType {
    CREDIT_CARD("Credit Card"),
    BANK_ACCOUNT("Bank Account"),
    PAYPAL("PayPal");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
