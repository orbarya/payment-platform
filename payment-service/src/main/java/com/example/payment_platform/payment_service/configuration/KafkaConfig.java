package com.example.payment_platform.payment_service.configuration;

import com.example.payment_platform.payment_service.configuration.properties.PaymentsKafkaProducerProperties;
import com.example.payment_platform.common.dto.PaymentDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(PaymentsKafkaProducerProperties.class)
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers;

    private final PaymentsKafkaProducerProperties paymentsKafkaProducerProperties;

    public KafkaConfig(PaymentsKafkaProducerProperties paymentsKafkaProducerProperties) {
        this.paymentsKafkaProducerProperties = paymentsKafkaProducerProperties;
    }

    @Bean
    public ProducerFactory<String, PaymentDto> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name(paymentsKafkaProducerProperties.name)
                .partitions(paymentsKafkaProducerProperties.partitions)
                .replicas(paymentsKafkaProducerProperties.replication)
                .build();
    }

    @Bean
    public KafkaTemplate<String, PaymentDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
