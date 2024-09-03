package com.example.payment_platform.riskengine.dto;

public class RiskEngineResult {

    boolean isPassed;
    double riskScore;

    public RiskEngineResult(boolean isPassed, double riskScore) {
        this.isPassed = isPassed;
        this.riskScore = riskScore;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }
}
