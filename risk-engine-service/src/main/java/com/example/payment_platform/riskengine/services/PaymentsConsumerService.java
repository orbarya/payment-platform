package com.example.payment_platform.riskengine.services;

import com.example.payment_platform.common.dto.PaymentDto;
import com.example.payment_platform.common.model.User;
import com.example.payment_platform.common.model.UserPaymentMethod;
import com.example.payment_platform.common.repository.PaymentMethodRepository;
import com.example.payment_platform.common.repository.UserRepository;
import com.example.payment_platform.riskengine.configuration.properties.PaymentsKafkaConsumerProperties;
import com.example.payment_platform.riskengine.dto.RiskEngineResult;
import com.example.payment_platform.riskengine.model.Payment;
import com.example.payment_platform.riskengine.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentsConsumerService {

    private final RiskEngineService riskEngineService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentsConsumerService.class);

    public final PaymentsKafkaConsumerProperties kafkaProperties;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentsConsumerService(RiskEngineService riskEngineService,
                                   UserRepository userRepository,
                                   PaymentMethodRepository paymentMethodRepository,
                                   PaymentsKafkaConsumerProperties kafkaProperties,
                                   PaymentRepository paymentRepository,
                                   ModelMapper modelMapper) {
        this.riskEngineService = riskEngineService;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.kafkaProperties = kafkaProperties;
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = "${com.example.payment.risk-engine.payments-topic.name}",
            groupId = "${com.example.payment.risk-engine.payments-topic.consumerGroup}")
    public void consume(PaymentDto paymentDto, Acknowledgment acknowledgment) {
        try {
            logger.debug(String.format("Received payment with id: " + paymentDto.getId()));

            RiskEngineResult riskResult = riskEngineService.calculatePaymentRisk(paymentDto);

            Payment payment = modelMapper.map(paymentDto, Payment.class);
            User payer = userRepository.getReferenceById(UUID.fromString(paymentDto.getPayerId()));
            User payee = userRepository.getReferenceById(UUID.fromString(paymentDto.getPayeeId()));
            UserPaymentMethod paymentMethod = paymentMethodRepository.getReferenceById(UUID.fromString(paymentDto.getPaymentMethodId()));
            payment.setPayer(payer);
            payment.setPayee(payee);
            payment.setPaymentMethod(paymentMethod);
            payment.setPassedRiskEngine(riskResult.isPassed());
            payment.setRiskScore(riskResult.getRiskScore());
            paymentRepository.save(payment);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            logger.error("Failed to process payment message with exception.", e);
        }
    }
}
