package com.example.payment_platform.payment_service.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.payment_platform.common.repository")
@EntityScan("com.example.payment_platform")
public class JpaConfig {
}
