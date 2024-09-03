package com.example.payment_platform.riskengine.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.payment.risk-engine-service")
public class RiskEngineProperties {

    private double riskEngineThreshold;

    public double getRiskEngineThreshold() {
        return riskEngineThreshold;
    }

    public void setRiskEngineThreshold(double riskEngineThreshold) {
        this.riskEngineThreshold = riskEngineThreshold;
    }
}
