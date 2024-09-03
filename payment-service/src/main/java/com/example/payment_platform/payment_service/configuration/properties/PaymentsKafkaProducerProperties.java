package com.example.payment_platform.payment_service.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "com.example.payment.payment-service.payments-topic")
public class PaymentsKafkaProducerProperties {
    public String name;
    public int partitions;
    public int replication;

}
