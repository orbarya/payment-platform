package com.example.payment_platform.riskengine.services;

import com.example.payment_platform.common.dto.PaymentDto;
import com.example.payment_platform.riskengine.configuration.properties.RiskEngineProperties;
import com.example.payment_platform.riskengine.dto.RiskEngineResult;
import org.springframework.stereotype.Service;

@Service
public class RiskEngineService {

    private final RiskEngineProperties riskEngineProperties;

    public RiskEngineService(RiskEngineProperties riskEngineProperties) {
        this.riskEngineProperties = riskEngineProperties;
    }

    RiskEngineResult calculatePaymentRisk(PaymentDto paymentDto) {
        double riskScore = Math.random();
        return new RiskEngineResult(riskScore > riskEngineProperties.getRiskEngineThreshold(), riskScore);
    }
}
