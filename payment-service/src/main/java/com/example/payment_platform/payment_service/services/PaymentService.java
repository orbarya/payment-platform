package com.example.payment_platform.payment_service.services;

import com.example.payment_platform.payment_service.configuration.properties.PaymentsKafkaProducerProperties;
import com.example.payment_platform.common.dto.PaymentDto;
import com.example.payment_platform.common.repository.PaymentMethodRepository;
import com.example.payment_platform.common.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final UserRepository userRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final KafkaTemplate<String, PaymentDto> kafkaTemplate;
    private final PaymentsKafkaProducerProperties paymentsKafkaProducerProperties;

    public PaymentService(UserRepository userRepository,
                          PaymentMethodRepository paymentMethodRepository,
                          KafkaTemplate<String, PaymentDto> kafkaTemplate,
                          PaymentsKafkaProducerProperties paymentsKafkaProducerProperties) {
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.paymentsKafkaProducerProperties = paymentsKafkaProducerProperties;
    }

    public PaymentDto create(PaymentDto paymentInputDto) {
        validateForeignKeys(paymentInputDto);
        PaymentDto payment = new PaymentDto(String.valueOf(UUID.randomUUID()), paymentInputDto);
        payment = producePaymentToKafka(payment);
        return payment;
    }

    private PaymentDto producePaymentToKafka(PaymentDto paymentDto) {
        try {
            SendResult<String, PaymentDto> result = kafkaTemplate.send(paymentsKafkaProducerProperties.name, paymentDto).get();
            return result.getProducerRecord().value();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error while producing the payment to kafka", e);
        }
    }

    private void validateForeignKeys(PaymentDto paymentDto) {
        // Check if payer exists
        if (!userRepository.existsById(UUID.fromString(paymentDto.getPayerId()))) {
            throw new IllegalArgumentException("Payer ID does not exist.");
        }

        // Check if payee exists
        if (!userRepository.existsById(UUID.fromString(paymentDto.getPayeeId()))) {
            throw new IllegalArgumentException("Payee ID does not exist.");
        }

        // Check if payment method exists
        if (!paymentMethodRepository.existsByIdAndUser_Id(
                UUID.fromString(paymentDto.getPaymentMethodId()),
                UUID.fromString(paymentDto.getPayerId()))) {
            throw new IllegalArgumentException("Payment Method ID does not exist for the user");
        }
    }
}

