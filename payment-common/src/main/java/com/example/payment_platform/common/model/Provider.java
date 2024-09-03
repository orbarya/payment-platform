package com.example.payment_platform.common.model;

public enum Provider {
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    PAYPAL("PayPal"),
    AMERICAN_EXPRESS("American Express"),
    DISCOVER("Discover");

    private final String displayName;

    Provider(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
