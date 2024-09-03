package com.example.payment_platform.riskengine;

import com.example.payment_platform.riskengine.configuration.properties.RiskEngineProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.payment_platform")
@EnableConfigurationProperties(RiskEngineProperties.class)
@EnableJpaRepositories("com.example.payment_platform")
public class RiskEngineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskEngineServiceApplication.class, args);
    }

}
