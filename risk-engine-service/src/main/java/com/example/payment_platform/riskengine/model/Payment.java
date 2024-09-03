package com.example.payment_platform.riskengine.model;

import com.example.payment_platform.common.model.User;
import com.example.payment_platform.common.model.UserPaymentMethod;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", schema = "public")
public class Payment {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payer_id")
    private User payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payee_id")
    private User payee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id")
    private UserPaymentMethod paymentMethod;

    @Column(name="amount")
    private double amount;

    @Column(name="currency")
    private String currency;

    @Column(name="risk_score")
    private double riskScore;

    @Column(name="is_passed_risk_engine")
    private boolean isPassedRiskEngine;

    @Column(name="created_at")
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    private OffsetDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public User getPayer() {
        return payer;
    }

    public User getPayee() {
        return payee;
    }

    public UserPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public boolean isPassedRiskEngine() {
        return isPassedRiskEngine;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public void setPassedRiskEngine(boolean passedRiskEngine) {
        isPassedRiskEngine = passedRiskEngine;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public void setPaymentMethod(UserPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
